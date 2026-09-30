package com.example.pe.edu.upc.ferova_mobile_android.data.remote.api


import com.example.pe.edu.upc.ferova_mobile_android.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AchievementApiService {

    @GET("api/achievements-rewards/patients/{patientId}/achievement")
    suspend fun getAchievementProgress(
        @Path("patientId") patientId: String
    ): Response<AchievementProgressDto>

    @GET("api/achievements-rewards/patients/{patientId}/badges")
    suspend fun getBadges(
        @Path("patientId") patientId: String
    ): Response<BadgesResponseDto>
}
