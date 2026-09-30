package com.example.pe.edu.upc.ferova_mobile_android.data.remote.api


import com.example.pe.edu.upc.ferova_mobile_android.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TreatmentApiService {

    @GET("api/treatment-tracking/patients/{patientId}/today-dose")
    suspend fun getTodayDose(
        @Path("patientId") patientId: String
    ): Response<TodayDoseDto>

    @GET("api/treatment-tracking/patients/{patientId}/dose-history")
    suspend fun getDoseHistory(
        @Path("patientId") patientId: String
    ): Response<DoseHistoryResponseDto>

    @POST("api/treatment-tracking/doses/confirm")
    suspend fun confirmDose(
        @Body request: ConfirmDoseRequest
    ): Response<DoseRecordDto>
}
