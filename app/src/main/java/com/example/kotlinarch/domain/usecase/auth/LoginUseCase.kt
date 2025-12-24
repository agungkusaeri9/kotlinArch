package com.example.kotlinarch.domain.usecase.auth

import com.example.kotlinarch.domain.repository.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String) =
        repository.login(username, password)
}