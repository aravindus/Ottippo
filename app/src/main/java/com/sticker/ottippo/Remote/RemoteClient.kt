package com.sticker.ottippo.Remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object RemoteClient {
    private var retrofit: Retrofit? = null


    private const val BASE_URL = "http://13.232.230.225/svarowellness/dev/api/v2/"
    private val httpClient: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(12000, java.util.concurrent.TimeUnit.SECONDS)
        .connectTimeout(12000, java.util.concurrent.TimeUnit.SECONDS).build()
    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .client(httpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

    class AddHeaderInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val builder = chain.request().newBuilder()
            builder.addHeader("Auth-sdk", "MKa6-rMEU-H75p-4zU3")
            return chain.proceed(builder.build())
        }
    }
}