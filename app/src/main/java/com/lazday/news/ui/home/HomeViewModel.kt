package com.lazday.news.ui.home

import androidx.lifecycle.ViewModel
import com.lazday.news.source.news.CategoryModel
import com.lazday.news.source.news.NewsRepository
import org.koin.dsl.module

val homeViewModel = module {
    factory { HomeViewModel( get() ) }
}

class HomeViewModel(val repository: NewsRepository): ViewModel() {
    val title = "Berita"

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