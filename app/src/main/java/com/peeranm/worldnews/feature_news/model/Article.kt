package com.peeranm.worldnews.feature_news.model

data class Article(
    val id: Long,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: String,
    val title: String,
    val url: String,
    val urlToImage: String
)