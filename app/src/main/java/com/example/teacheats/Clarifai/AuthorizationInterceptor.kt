package com.example.teacheats

import android.content.Context
import com.squareup.moshi.Moshi
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response


class AuthorizationInterceptor(val apiId: String, val apiSecret: String, val context: Context) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        // Get request path.
        val uri = chain?.request()?.url()?.uri()
        val path = uri?.path

        val authValue: String
        if (path == "/v2/token") {
            authValue = Credentials.basic(apiId, apiSecret)
        } else {
            val prefs = context.getSharedPreferences(App.PREFS_NAME, Context.MODE_PRIVATE)
            val authString = prefs.getString(App.AUTH_TOKEN_KEY, "")
            val authResponse = Moshi.Builder().build().adapter(AuthToken::class.java).fromJson(authString)
            authValue = "Bearer ${authResponse?.accessToken}"
        }

        val request = chain?.request()?.newBuilder()?.addHeader("Authorization", authValue)?.build()

        return chain?.proceed(request)!!
    }
}