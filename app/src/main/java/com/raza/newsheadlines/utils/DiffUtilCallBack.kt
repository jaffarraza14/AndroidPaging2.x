package com.raza.newsheadlines.utils

import androidx.recyclerview.widget.DiffUtil
import com.raza.newsheadlines.source.model.NewsResponse

class DiffUtilCallBack : DiffUtil.ItemCallback<NewsResponse.Article>() {
    override fun areItemsTheSame(oldItem: NewsResponse.Article, newItem: NewsResponse.Article): Boolean {
        return oldItem.urlToImage == newItem.urlToImage
    }

    override fun areContentsTheSame(oldItem: NewsResponse.Article, newItem: NewsResponse.Article): Boolean {
        return oldItem == newItem
    }

}