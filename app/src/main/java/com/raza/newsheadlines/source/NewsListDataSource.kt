package com.raza.newsheadlines.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.raza.newsheadlines.source.model.NewsResponse
import com.raza.newsheadlines.source.network.Api
import com.raza.newsheadlines.source.network.RetrofitClient

class NewsListDataSource : PagingSource<Int, NewsResponse.Article>() {
    private val apiService = RetrofitClient.getClient().create(Api::class.java)


   /* override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsResponse.Article> {
        try {
            val response =
                apiService.getHeadlines(page = params.key)
            when{
                response.isSuccessful -> {
                    val articles = response.body()?.articles
                    val next = params.key?.let { it  + 1 }
                    val previousKey = params.key
                    articles?.let {
                        return LoadResult.Page(articles, previousKey, next)
                    }
                }
            }

        }catch (exception : Exception){
            Log.e("NewsListDataSource", "Failed to fetch data!")
        }
    }*/

    override fun getRefreshKey(state: PagingState<Int, NewsResponse.Article>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsResponse.Article> {
        try {
            val response =
                apiService.getHeadlines(page = params.key)
            params
            when{
                response.isSuccessful -> {
                    val articles = response.body()?.articles
                    val next = params.key?.let { it  + 1 }
                    val previousKey = params.key
                    articles?.let {
                        return LoadResult.Page(articles, previousKey, next)
                    }
                }
            }

        }catch (exception : Exception){
            Log.e("NewsListDataSource", "Failed to fetch data!")
        }
        return LoadResult.Page(listOf(), 0, 1)
    }

}