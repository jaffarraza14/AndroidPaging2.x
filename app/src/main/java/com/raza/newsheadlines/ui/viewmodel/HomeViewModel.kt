package com.raza.newsheadlines.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.raza.newsheadlines.source.NewsListRepository
import com.raza.newsheadlines.source.model.NewsResponse


class HomeViewModel(private val repository: NewsListRepository) : ViewModel() {
    var topHeadlines  : LiveData<PagingData<NewsResponse.Article>>

    init {
        this.topHeadlines = articlesData()
    }


    private fun articlesData(): LiveData<PagingData<NewsResponse.Article>> {
        return repository.getPager().liveData
    }
}