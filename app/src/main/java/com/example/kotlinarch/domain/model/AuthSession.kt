package com.example.kotlinarch.domain.model

data class AuthSession(
    val user: User,
    val accessToken: String,
    val tokenType: String,
    val expiredAt: String
)