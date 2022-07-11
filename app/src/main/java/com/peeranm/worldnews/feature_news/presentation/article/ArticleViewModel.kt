package com.peeranm.worldnews.feature_news.presentation.article

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun onEvent(event: ArticleEvent) {
        when (event) {
            is ArticleEvent.SaveArticle -> viewModelScope.launch {
                articleUseCases.insertFavArticle(
                    FavArticle(
                        title = event.article.title,
                        url = event.article.url,
                        author = event.article.author,
                        content  = event.article.content,
                        description = event.article.description,
                        publishedAt = event.article.publishedAt,
                        source = event.article.source,
                        urlToImage = event.article.urlToImage
                    )
                )
            }.invokeOnCompletion {
                _uiAction.value = UiAction.ShowMessage("Added to favourites!")
            }
        }
    }
}