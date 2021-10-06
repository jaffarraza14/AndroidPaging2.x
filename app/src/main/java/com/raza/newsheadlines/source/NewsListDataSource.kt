package com.raza.newsheadlines.source

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.raza.newsheadlines.source.model.NewsResponse
import com.raza.newsheadlines.source.network.Api
import com.raza.newsheadlines.source.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NewsListDataSource(coroutineContext: CoroutineContext) :
    PageKeyedDataSource<Int, NewsResponse.Article>() {
    private val apiService = RetrofitClient.getClient().create(Api::class.java)

    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, NewsResponse.Article>) {
        scope.launch {
            try {
                val response = apiService.getHeadlines(pageSize = params.requestedLoadSize)
                when{
                    response.isSuccessful -> {
                        val articles = response.body()?.articles
                        callback.onResult(articles ?: listOf(), null, 1)
                    }
                }
            }catch (exception : Exception){
                Log.e("NewsListDataSource", exception.toString())
            }

        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, NewsResponse.Article>) {
        scope.launch {
            try {
                val response =
                    apiService.getHeadlines(pageSize = params.requestedLoadSize, page = params.key)
                when{
                    response.isSuccessful -> {
                        val articles = response.body()?.articles
                        var next = params.key
                        next+=1
                        callback.onResult(articles ?: listOf(), next)
                    }
                }

            }catch (exception : Exception){
                Log.e("NewsListDataSource", "Failed to fetch data!")
            }
        }

    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, NewsResponse.Article>
    ) {

    }

}