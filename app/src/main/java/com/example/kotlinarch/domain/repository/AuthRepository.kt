package com.example.kotlinarch.domain.repository

import com.example.kotlinarch.domain.model.AuthSession

interface AuthRepository {
    suspend fun login(username: String, password: String): AuthSession
}