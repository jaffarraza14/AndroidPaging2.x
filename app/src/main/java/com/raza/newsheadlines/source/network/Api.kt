package com.raza.newsheadlines.source.network

import com.raza.newsheadlines.source.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("pageSize") pageSize: Int = 5,
        @Query("page") page: Int? = 0,
        @Query("country") before: String? = "us"
    ): Response<NewsResponse>
}