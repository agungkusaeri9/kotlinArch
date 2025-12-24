package com.example.kotlinarch.data.remote.api

import com.example.kotlinarch.data.remote.dto.LoginRequestDto
import com.example.kotlinarch.data.remote.dto.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("api/auth/login")
    suspend fun login(
        @Body body: LoginRequestDto
    ): LoginResponseDto
}