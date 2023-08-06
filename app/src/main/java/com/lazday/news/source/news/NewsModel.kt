package com.lazday.news.source.news

import java.io.Serializable

data class NewsModel(
    val status: String,
    val totalResult: Int,
    val articles: List<ArticleModel>
)

data class ArticleModel(
    val source: SourceModel?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
) : Serializable

data class SourceModel(
    val id: String?,
    val name: String?
) : Serializable