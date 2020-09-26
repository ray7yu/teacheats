package com.example.teacheats
import com.squareup.moshi.Json

data class AuthToken(
    @Json(name = "access_token") val accessToken: String? = "",
    @Json(name = "expires_in") val expiresIn: Int? = 0
)
