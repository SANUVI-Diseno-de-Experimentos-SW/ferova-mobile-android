package com.example.pe.edu.upc.ferova_mobile_android.data.remote.api


import com.example.pe.edu.upc.ferova_mobile_android.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface HealthFacilitiesApiService {

    @GET("api/health-facilities/nearby")
    suspend fun getNearbyFacilities(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double
    ): Response<List<HealthFacilityResponse>>

    @GET("api/health-facilities/{id}")
    suspend fun getFacilityDetail(@Path("id") id: String): Response<HealthFacilityResponse>

    @GET("api/health-facilities/{facilityId}/available-slots")
    suspend fun getAvailableSlots(
        @Path("facilityId") facilityId: String,
        @Query("date") date: String   // "2026-06-10"
    ): Response<List<AvailableSlotsResponse>>

    @POST("api/health-facilities/appointments")
    suspend fun bookAppointment(
        @Body request: BookAppointmentRequest
    ): Response<BookAppointmentResponse>

    @PUT("api/health-facilities/appointments/cancel")
    suspend fun cancelAppointment(
        @Body request: CancelAppointmentRequest
    ): Response<CancelAppointmentResponse>

    @GET("api/health-facilities/patient/{patientId}/appointments")
    suspend fun getPatientAppointments(
        @Path("patientId") patientId: String
    ): Response<List<AppointmentResponse>>

    @GET("api/health-facilities/appointments/mother/next")
    suspend fun getMotherNextAppointment(): Response<NextAppointmentResponse>
}
