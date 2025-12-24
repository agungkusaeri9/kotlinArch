package com.example.kotlinarch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlinarch.feature.auth.login.LoginScreen
import com.example.kotlinarch.feature.auth.login.LoginViewModel
import com.example.kotlinarch.feature.auth.login.LoginVmFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val vm: LoginViewModel = viewModel(
                        factory = LoginVmFactory(LocalContext.current.applicationContext as android.app.Application)
                    )

                    var loggedIn by remember { mutableStateOf(false) }

                    if (!loggedIn) {
                        LoginScreen(
                            vm = vm,
                            onSuccess = { loggedIn = true }
                        )
                    } else {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("HOME ✅ (login success)")
                        }
                    }
                }
            }
        }
    }
}
