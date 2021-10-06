package com.raza.newsheadlines.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.raza.newsheadlines.source.NewsListDataSource
import com.raza.newsheadlines.source.model.NewsResponse
import kotlinx.coroutines.Dispatchers


class HomeViewModel : ViewModel() {
    private var topHeadlines  : LiveData<PagedList<NewsResponse.Article>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(false)
            .build()
        topHeadlines  = initializedPagedListBuilder(config).build()
    }

    fun getHeadlines(): LiveData<PagedList<NewsResponse.Article>> = topHeadlines

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, NewsResponse.Article> {

        val dataSourceFactory = object : DataSource.Factory<Int, NewsResponse.Article>() {
            override fun create(): DataSource<Int, NewsResponse.Article> {
                    return NewsListDataSource(Dispatchers.IO)
            }

        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }
}