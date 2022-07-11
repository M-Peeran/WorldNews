package com.peeranm.worldnews.feature_news.use_cases

import androidx.paging.PagingData
import com.peeranm.worldnews.feature_news.data.repository.NewsRepository
import com.peeranm.worldnews.feature_news.model.Article
import kotlinx.coroutines.flow.Flow


class GetHeadlinesUseCase(private val repository: NewsRepository) {
    operator fun invoke(): Flow<PagingData<Article>> = repository.getHeadlines()
}