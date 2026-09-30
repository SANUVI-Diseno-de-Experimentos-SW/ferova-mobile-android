package com.example.pe.edu.upc.ferova_mobile_android.presentation.appointments.utils

import android.graphics.Color
import okhttp3.OkHttpClient
import okhttp3.Request
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Polyline

data class RoutePoint(
    val latitude: Double,
    val longitude: Double
)

data class RouteInfo(
    val points: List<RoutePoint>,
    val distanceKm: Double,
    val durationMinutes: Int
)

class RouteHelper {
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .header("User-Agent", "FerovaFamily/1.0 (tucorreo@upc.edu.pe)")
                    .build()
            )
        }
        .build()
    private val gson = Gson()

    suspend fun getRoute(start: GeoPoint, end: GeoPoint): RouteInfo? = withContext(Dispatchers.IO) {
        try {
            val url = "https://router.project-osrm.org/route/v1/driving/${start.longitude},${start.latitude};${end.longitude},${end.latitude}?overview=full&geometries=geojson"

            val request = Request.Builder()
                .url(url)
                .build()

            client.newCall(request).execute().use { response ->
                val jsonString = response.body?.string()
                if (response.isSuccessful && jsonString != null) {
                    parseRouteResponse(jsonString)
                } else {
                    android.util.Log.w("RouteHelper", "OSRM respondió ${response.code}")
                    null
                }
            }
        } catch (e: Exception) {
            android.util.Log.e("RouteHelper", "Error obteniendo ruta", e)
            null
        }
    }

    private fun parseRouteResponse(json: String): RouteInfo? {
        return try {
            val jsonObject = gson.fromJson(json, Map::class.java)
            val routes = jsonObject["routes"] as? List<*>
            val firstRoute = routes?.firstOrNull() as? Map<*, *>

            if (firstRoute != null) {
                val geometry = firstRoute["geometry"] as? Map<*, *>
                val coordinates = geometry?.get("coordinates") as? List<*>

                val points = coordinates?.mapNotNull { coord ->
                    val coordList = coord as? List<*>
                    if (coordList?.size == 2) {
                        val lng = coordList[0] as? Double
                        val lat = coordList[1] as? Double
                        if (lat != null && lng != null) {
                            RoutePoint(lat, lng)
                        } else null
                    } else null
                } ?: emptyList()

                val distance = (firstRoute["distance"] as? Double)?.let { it / 1000.0 } ?: 0.0
                val duration = (firstRoute["duration"] as? Double)?.let { it.toInt() / 60 } ?: 0

                RouteInfo(
                    points = points,
                    distanceKm = distance,
                    durationMinutes = duration
                )
            } else null
        } catch (e: Exception) {
            null
        }
    }

    fun drawRoute(mapView: MapView, routePoints: List<RoutePoint>): Polyline {
        val polyline = Polyline().apply {
            setPoints(routePoints.map { GeoPoint(it.latitude, it.longitude) })
            color = Color.parseColor("#8B1A1A")
            width = 8f
        }
        mapView.overlays.add(polyline)
        mapView.invalidate()
        return polyline
    }

    fun clearRoutes(mapView: MapView) {
        val toRemove = mapView.overlays.filterIsInstance<Polyline>()
        mapView.overlays.removeAll(toRemove)
        mapView.invalidate()
    }

    fun getRoutes(mapView: MapView): List<Polyline> {
        return mapView.overlays.filterIsInstance<Polyline>()
    }
}