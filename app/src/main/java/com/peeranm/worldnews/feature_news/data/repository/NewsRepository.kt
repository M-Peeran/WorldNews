package com.peeranm.worldnews.feature_news.data.repository

import androidx.paging.PagingData
import com.peeranm.worldnews.feature_news.data.local.entity.FavArticle
import com.peeranm.worldnews.feature_news.model.Article
import kotlinx.coroutines.flow.Flow


interface NewsRepository {
    fun getHeadlines(): Flow<PagingData<Article>>
    fun searchNews(searchQuery: String): Flow<PagingData<Article>>
    fun getFavArticles(): Flow<List<FavArticle>>
    suspend fun getArticleById(id: Int): Article?
    suspend fun insertFavArticle(favArticle: FavArticle)
    suspend fun deleteFavArticleById(id: Long)
    suspend fun getFavArticleById(id: Long): FavArticle?
}