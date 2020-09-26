package com.example.teacheats

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.teacheats.databinding.ActivityMainBinding
import com.squareup.moshi.Moshi
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    var manager: ClarifaiManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        manager = ClarifaiManager(this, getApiId(), getApiSecret())
        authorizeUser()

    }

    private fun authorizeUser() {
        val call = manager?.authorize(RequestBody.create(MEDIA_TYPE_JSON, GRANT_TYPE_CREDENTIALS))

        call?.enqueue(object : Callback<AuthToken> {
            override fun onFailure(call: Call<AuthToken>?, t: Throwable?) {
                Timber.e(t)
            }

            override fun onResponse(call: Call<AuthToken>?, response: Response<AuthToken>?) {
                Timber.v("Success! Token ${response?.body()?.accessToken}")

                val authString =
                    Moshi.Builder().build().adapter(AuthToken::class.java).toJson(response?.body())
                val prefs = getSharedPreferences(App.PREFS_NAME, Context.MODE_PRIVATE)
                val editor = prefs.edit()
                editor.putString(App.AUTH_TOKEN_KEY, authString)
                editor.apply()
            }
        })
    }

    companion object {
        private val MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf8")
        private val GRANT_TYPE_CREDENTIALS = "\"grant_type\":\"client_credentials\""
    }

    init {
        System.loadLibrary("native-lib")
    }

    external fun getApiSecret(): String
    external fun getApiId(): String
}
