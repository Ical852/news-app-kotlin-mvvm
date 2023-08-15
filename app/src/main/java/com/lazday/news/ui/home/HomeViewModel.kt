package com.lazday.news.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lazday.news.source.news.ArticleModel
import com.lazday.news.source.news.CategoryModel
import com.lazday.news.source.news.NewsModel
import com.lazday.news.source.news.NewsRepository
import kotlinx.coroutines.launch
import org.koin.dsl.module
import java.lang.Exception

val homeViewModel = module {
    factory { HomeViewModel( get() ) }
}

class HomeViewModel(val repository: NewsRepository): ViewModel() {
    val title = "Berita"
    val category by lazy { MutableLiveData<String>() }
    val message by lazy { MutableLiveData<String>() }
    val news by lazy { MutableLiveData<NewsModel>() }
    val loading by lazy { MutableLiveData<Boolean>() }
    val search by lazy { MutableLiveData<String>("") }
    val page by lazy { MutableLiveData<Int>(1) }

    init {
        category.value = ""
        message.value = null
        fetch()
    }

    fun fetch(cat: String? = category.value, type: String = "normal") {
        loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.fetch(cat!!, search.value!!, page.value!!)
                var newData = response

                if (type == "extend") {
                    var setupList: ArrayList<ArticleModel> = ArrayList()
                    setupList.addAll(news.value!!.articles)
                    setupList.addAll(response.articles)
                    newData.articles = setupList
                }

                news.value = newData
                loading.value = false
            } catch (e: Exception) {
                message.value = "Terjadi Kesalahan " + e.message
                loading.value = false
            }
        }
    }

    val categries = listOf<CategoryModel>(
        CategoryModel("", "Berita Utama"),
        CategoryModel("business", "Bisnis"),
        CategoryModel("entertainment", "Hiburan"),
        CategoryModel("general", "Umum"),
        CategoryModel("health", "Kesehatan"),
        CategoryModel("science", "Sains"),
        CategoryModel("sports", "Olahraga"),
        CategoryModel("technology", "Teknologi"),
    )
}