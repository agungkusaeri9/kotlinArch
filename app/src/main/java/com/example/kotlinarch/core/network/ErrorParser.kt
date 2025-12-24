package com.example.kotlinarch.core.network

import com.google.gson.Gson
import retrofit2.HttpException

data class ApiErrorDto(
    val status: Boolean? = null,
    val message: String? = null
)

fun Throwable.toApiMessage(): String {
    return when (this) {
        is HttpException -> {
            val raw = runCatching { response()?.errorBody()?.string() }.getOrNull()

            // 1) kalau body ada dan JSON sesuai {message: "..."}
            val parsed = runCatching { Gson().fromJson(raw, ApiErrorDto::class.java) }
                .getOrNull()
                ?.message

            // 2) fallback: kalau body plain text / json beda, tampilkan raw (dipotong biar ga norak)
            when {
                !parsed.isNullOrBlank() -> parsed
                !raw.isNullOrBlank() -> raw.take(200)
                else -> "HTTP ${code()} ${message()}"
            }
        }
        else -> message ?: "Terjadi kesalahan"
    }
}
