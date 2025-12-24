package com.example.kotlinarch.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinarch.core.network.toApiMessage
import com.example.kotlinarch.domain.usecase.auth.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    val state = MutableStateFlow(LoginUiState())

    fun onUsernameChange(v: String) =
        state.update { it.copy(username = v) }

    fun onPasswordChange(v: String) =
        state.update { it.copy(password = v) }

    fun login() {
        val s = state.value
        if (s.username.isBlank() || s.password.isBlank()) {
            state.update { it.copy(error = "Username & password wajib diisi") }
            return
        }

        viewModelScope.launch {
            state.update { it.copy(loading = true, error = null) }
            runCatching {
                loginUseCase(s.username, s.password)
            }.onSuccess {
                state.update { it.copy(loading = false, success = true) }
            }.onFailure { throwable ->
                val msg = throwable.toApiMessage()

                println("error guys $msg")

                state.update {
                    it.copy(
                        loading = false,
                        error = msg
                    )
                }
            }

        }
    }

    fun clearError() {
        state.update { it.copy(error = null) }
    }

}
