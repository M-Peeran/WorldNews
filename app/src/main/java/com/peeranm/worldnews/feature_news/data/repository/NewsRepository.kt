package com.peeranm.worldnews.feature_news.data.repository

import androidx.paging.PagingData
import com.peeranm.worldnews.feature_news.model.Article
import kotlinx.coroutines.flow.Flow


interface NewsRepository {
    fun getHeadlines(): Flow<PagingData<Article>>
    fun searchNews(searchQuery: String): Flow<PagingData<Article>>
}