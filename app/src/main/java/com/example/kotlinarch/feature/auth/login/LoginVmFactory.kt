package com.example.kotlinarch.feature.auth.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinarch.core.network.ApiClient
import com.example.kotlinarch.core.storage.TokenDataStore
import com.example.kotlinarch.data.repository.AuthRepositoryImpl
import com.example.kotlinarch.domain.usecase.auth.LoginUseCase

class LoginVmFactory(
    private val app: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {

            val tokenStore = TokenDataStore(app)
            val repo = AuthRepositoryImpl(ApiClient.authApi, tokenStore)
            val useCase = LoginUseCase(repo)

            @Suppress("UNCHECKED_CAST")
           return LoginViewModel(useCase) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
