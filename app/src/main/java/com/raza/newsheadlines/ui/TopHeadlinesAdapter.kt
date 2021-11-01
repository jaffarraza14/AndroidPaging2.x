package com.raza.newsheadlines.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raza.newsheadlines.R
import com.raza.newsheadlines.source.model.NewsResponse
import com.raza.newsheadlines.utils.DiffUtilCallBack

class TopHeadlinesAdapter(val callback: (NewsResponse.Article) -> Unit) : PagingDataAdapter<NewsResponse.Article, TopHeadlinesAdapter.HeadlinesItemViewHolder>(DiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlinesItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.headlines_item, parent, false)
        return HeadlinesItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeadlinesItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindTopHeadline(it)
            holder.itemView.setOnClickListener {
                getItem(position)?.let { it1 -> callback(it1) }
            }
        }
    }

    class HeadlinesItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val titleTv: TextView = itemView.findViewById(R.id.title)
        private val descriptionTv: TextView = itemView.findViewById(R.id.description)
        private val authorTv: TextView = itemView.findViewById(R.id.author)

        @SuppressLint("SetTextI18n")
        fun bindTopHeadline(topHeadLine : NewsResponse.Article){
            with(topHeadLine){
                titleTv.text = title
                descriptionTv.text = description
                authorTv.visibility = View.GONE
                if (!author.isNullOrEmpty()) {
                    authorTv.text = "By: $author"
                    authorTv.visibility = View.VISIBLE
                }
            }
        }
    }
}