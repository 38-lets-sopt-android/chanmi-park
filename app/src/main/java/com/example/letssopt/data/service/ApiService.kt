package com.example.letssopt.data.service

import com.example.letssopt.data.dto.profile.ProfileResponse
import com.example.letssopt.data.dto.signup.SignUpRequest
import com.example.letssopt.data.dto.signup.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {
    @POST("api/v1/auth/signup")
    suspend fun signUp(
        @Body request: SignUpRequest
    ): Response<SignUpResponse>

    @GET("api/v1/users/{userId}")
    suspend fun profile(
        @Path("userId") userId: String
    ): Response<ProfileResponse>
}