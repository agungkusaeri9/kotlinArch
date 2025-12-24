package com.example.kotlinarch.data.repository

import com.example.kotlinarch.core.storage.TokenDataStore
import com.example.kotlinarch.data.remote.api.AuthApi
import com.example.kotlinarch.data.remote.dto.LoginRequestDto
import com.example.kotlinarch.domain.model.AuthSession
import com.example.kotlinarch.domain.model.User
import com.example.kotlinarch.domain.repository.AuthRepository

// data/repository/AuthRepositoryImpl.kt
class AuthRepositoryImpl(
    private val api: AuthApi,
    private val tokenStore: TokenDataStore
) : AuthRepository {

    override suspend fun login(username: String, password: String): AuthSession {
        val response = api.login(LoginRequestDto(username, password))

        if (!response.status) {
            throw Exception(response.message)
        }

        val data = response.data

        tokenStore.saveToken(data.accessToken.token)

        return AuthSession(
            user = User(
                id = data.user.userId,
                name = data.user.name,
                username = data.user.username,
                role = data.user.role
            ),
            accessToken = data.accessToken.token,
            tokenType = data.accessToken.tokenType,
            expiredAt = data.accessToken.expiredAt
        )
    }
}
