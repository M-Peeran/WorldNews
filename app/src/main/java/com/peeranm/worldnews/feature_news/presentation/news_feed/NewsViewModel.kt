package com.peeranm.worldnews.feature_news.presentation.news_feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.peeranm.worldnews.feature_news.model.Article
import com.peeranm.worldnews.feature_news.use_cases.ArticleUseCases
import com.peeranm.worldnews.feature_news.utils.CountryCode
import com.peeranm.worldnews.feature_news.utils.NewsCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalPagingApi::class)
class NewsViewModel @Inject constructor(
    private val articleUseCases: ArticleUseCases
) : ViewModel() {

    private val _articles = MutableStateFlow<PagingData<Article>>(PagingData.empty())
    val articles: StateFlow<PagingData<Article>> = _articles

    init { getTrendingNews() }

    fun getTrendingNews(category: NewsCategory = NewsCategory.GENERAL, countryCode: CountryCode = CountryCode.INDIA) {
        articleUseCases.getTrendingNews(category = category.categoryName, countryCode = countryCode.code)
            .cachedIn(viewModelScope)
            .onEach { _articles.value = it }
            .launchIn(viewModelScope)
    }

}