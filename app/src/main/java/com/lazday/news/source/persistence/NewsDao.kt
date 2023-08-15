package com.lazday.news.source.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lazday.news.source.news.ArticleModel

@Dao
interface NewsDao {
    @Query("SELECT * FROM tableArticle")
    fun findAll(): LiveData<List<ArticleModel>>

    @Query("SELECT COUNT(*) FROM tableArticle WHERE publishedAt=:publish")
    suspend fun find(publish: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(articleModel: ArticleModel)

    @Delete
    suspend fun remove(articleModel: ArticleModel)
}