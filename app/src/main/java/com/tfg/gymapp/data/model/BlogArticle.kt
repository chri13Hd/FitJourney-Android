package com.tfg.gymapp.data.model

data class NewsResponse(
    val articles: List<BlogArticle>
)

data class BlogArticle(
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val source: Source
)

data class Source(
    val name: String
)