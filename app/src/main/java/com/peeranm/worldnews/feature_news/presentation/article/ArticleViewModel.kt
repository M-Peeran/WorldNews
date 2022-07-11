package com.peeranm.worldnews.feature_news.presentation.article

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peeranm.worldnews.core.Constants
import com.peeranm.worldnews.feature_news.data.local.entity.FavArticle
import com.peeranm.worldnews.feature_news.use_cases.ArticleUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val articleUseCases: ArticleUseCases
) : ViewModel() {

    private val _uiAction = MutableStateFlow<UiAction>(UiAction.None)
    val uiAction: StateFlow<UiAction> = _uiAction

    init {
        viewModelScope.launch {
            val selectedArticleId = savedStateHandle.get<Long>(Constants.ARG_SELECTED_ARTICLE_ID) ?: return@launch
            val article = articleUseCases.getArticle(selectedArticleId.toInt()) ?: return@launch
            _uiAction.value = UiAction.LoadArticle(article.url)
        }
    }

    fun onEvent(event: ArticleEvent) {
        when (event) {
            is ArticleEvent.SaveArticle -> viewModelScope.launch {
                val articleId = savedStateHandle.get<Long>(Constants.ARG_SELECTED_ARTICLE_ID) ?: return@launch
                val article = articleUseCases.getArticle(articleId.toInt()) ?: return@launch
                articleUseCases.insertFavArticle(
                    FavArticle(
                        title = article.title,
                        url = article.url,
                        author = article.author,
                        content  = article.content,
                        description = article.description,
                        publishedAt = article.publishedAt,
                        source = article.source,
                        urlToImage = article.urlToImage
                    )
                )
            }.invokeOnCompletion {
                _uiAction.value = UiAction.ShowMessage("Added to favourites!")
            }
        }
    }
}