package com.example.kotlinarch.feature.auth.login

data class LoginUiState(
    val username: String = "agung",
    val password: String = "password",
    val loading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)