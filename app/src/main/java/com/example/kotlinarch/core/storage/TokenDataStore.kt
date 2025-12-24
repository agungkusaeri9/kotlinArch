package com.example.kotlinarch.core.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("auth_prefs")

class TokenDataStore(private val context: Context) {

    private val accessTokenKey = stringPreferencesKey("access_token")

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[accessTokenKey] = token
        }
    }

    val token = context.dataStore.data.map { preferences ->
        preferences[accessTokenKey]
    }
}
