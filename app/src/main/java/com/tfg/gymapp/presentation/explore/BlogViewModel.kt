package com.tfg.gymapp.presentation.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfg.gymapp.data.api.RetrofitNewsClient
import com.tfg.gymapp.data.model.BlogArticle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BlogViewModel : ViewModel() {
    private val _articles = MutableStateFlow<List<BlogArticle>>(emptyList())
    val articles: StateFlow<List<BlogArticle>> = _articles

    private val apiKey = "f69573ca00484d9491245abd9e8ef487"

    init {
        viewModelScope.launch {
            try {
                val response = RetrofitNewsClient.api.getFitnessArticles(apiKey = apiKey)
                _articles.value = response.articles
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}