package com.example.pe.edu.upc.ferova_mobile_android.data.remote.api


import com.example.pe.edu.upc.ferova_mobile_android.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApiService {

    @POST("api/users/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("api/users/register/mother")
    suspend fun registerMother(@Body request: RegisterMotherRequest): Response<UserResponse>

    @GET("api/users/{id}")
    suspend fun getUserById(@Path("id") id: String): Response<UserResponse>

    @POST("api/users/password/request-code")
    suspend fun requestPasswordCode(@Body request: RequestPasswordCodeRequest): Response<MessageResponse>

    @POST("api/users/password/verify-code")
    suspend fun verifyPasswordCode(@Body request: VerifyPasswordCodeRequest): Response<MessageResponse>

    @POST("api/users/password/reset")
    suspend fun resetPassword(@Body request: ResetPasswordRequest): Response<MessageResponse>

    @GET("api/users/email/{email}")
    suspend fun getUserByEmail(@Path("email") email: String): Response<UserResponse>
}
