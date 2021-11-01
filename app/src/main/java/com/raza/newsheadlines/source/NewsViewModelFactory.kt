package com.raza.newsheadlines.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raza.newsheadlines.ui.viewmodel.HomeViewModel

internal class NewsViewModelFactory(
    private val repository: NewsListRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}