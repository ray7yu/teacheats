package com.example.teacheats

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ClarifaiAPI {
    @POST("/v2/token")
    fun authorize(@Body request: RequestBody): Call<AuthToken>
}