package com.example.teacheats

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ClarifaiManager(context: Context, apiId: String, apiSecret: String) {
    private val clarifaiApi: ClarifaiAPI
    init {
        val authInterceptor = AuthorizationInterceptor(apiId, apiSecret, context)
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(authInterceptor).addInterceptor(loggingInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.clarifai.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()

        clarifaiApi = retrofit.create(ClarifaiAPI::class.java)
    }
    fun authorize(requestBody: RequestBody): Call<AuthToken> {
        return clarifaiApi.authorize(requestBody)
    }
}