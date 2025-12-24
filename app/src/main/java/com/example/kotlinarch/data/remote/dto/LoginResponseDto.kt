package com.example.kotlinarch.data.remote.dto

data class LoginResponseDto(
    val status: Boolean,
    val message: String,
    val data: LoginDataDto
)

data class LoginDataDto(
    val user: UserDto,
    val accessToken: AccessTokenDto
)

data class UserDto(
    val userId: Int,
    val name: String,
    val username: String,
    val role: String
)

data class AccessTokenDto(
    val token: String,
    val expiredAt: String,
    val tokenType: String
)