package com.lazday.news.ui.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.R
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lazday.news.databinding.AdapterNewsBinding
import com.lazday.news.source.news.ArticleModel
import com.lazday.news.util.DateUtil

class NewsAdapter(
    private val articles: ArrayList<ArticleModel>,
    private val listener: OnAdapterListener
): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    interface OnAdapterListener {
        fun onClick(article: ArticleModel)
    }
    class ViewHolder(val binding: AdapterNewsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterNewsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.binding.title.text = article.title
        holder.binding.publishedAt.text = DateUtil().dateFormat(article.publishedAt)
        Glide.with(holder.itemView.context)
            .load(article.urlToImage)
            .into(holder.binding.image)
        holder.itemView.setOnClickListener {
            listener.onClick(article)
        }
    }

    override fun getItemCount() = articles.size

    @SuppressLint("NotifyDataSetChanged")
    fun add(data: List<ArticleModel>) {
        articles.clear()
        articles.addAll(data)
        notifyDataSetChanged()
    }
}