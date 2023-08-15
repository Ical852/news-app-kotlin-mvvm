package com.lazday.news.source.news

import com.lazday.news.BuildConfig
import com.lazday.news.source.network.ApiClient
import com.lazday.news.source.persistence.NewsDao
import org.koin.dsl.module

val repositoryModule = module {
    factory { NewsRepository( get(), get() ) }
}

class NewsRepository(
    private val api: ApiClient,
    val db: NewsDao
) {
    suspend fun fetch(
        category: String, query: String, page: Int
    ): NewsModel {
        return api.fetchNews(
            BuildConfig.API_KEY,
            "id",
            category,
            query,
            page
        )
    }

    fun findALl() = db.findAll()

    suspend fun find(articleModel: ArticleModel) = db.find(articleModel.publishedAt)

    suspend fun save(articleModel: ArticleModel) {
        db.save(articleModel)
    }

    suspend fun remove(articleModel: ArticleModel) {
        db.remove(articleModel)
    }
}