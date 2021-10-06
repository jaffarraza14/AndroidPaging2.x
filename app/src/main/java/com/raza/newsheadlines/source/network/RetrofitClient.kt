package com.raza.newsheadlines.source.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private const val BASE_URL: String = "https://newsapi.org/v2/"
        private val loggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        private val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ApiKeyInterceptor())
            .build()


        private var retrofit: Retrofit? = null

        fun getClient(): Retrofit {
            when (retrofit) {
                null -> retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .client(okHttpClient)
                    .build()
            }
            return retrofit as Retrofit
        }
    }
}