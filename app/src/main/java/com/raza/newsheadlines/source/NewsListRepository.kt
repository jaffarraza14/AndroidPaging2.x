package com.raza.newsheadlines.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.raza.newsheadlines.source.model.NewsResponse

class NewsListRepository(
    private val pageSize: Int
) {

    fun getPager(): Pager<Int, NewsResponse.Article> {
        return Pager(
            pagingSourceFactory = { NewsListDataSource() },
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 10,
                enablePlaceholders = false
            )
        )
    }
}