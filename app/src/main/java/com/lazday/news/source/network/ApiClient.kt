package com.lazday.news.source.network

import com.lazday.news.source.news.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("top-headlines")
    suspend fun fetchNews(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("q") q: String,
        @Query("page") page: Int,
    ) : NewsModel

}