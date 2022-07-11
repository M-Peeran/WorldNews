package com.peeranm.worldnews.feature_news.presentation.favourite_articles

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FavouriteArticlesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiAction = MutableStateFlow<UiAction>(UiAction.None)
    val uiAction: StateFlow<UiAction> = _uiAction

    fun onEvent(event: FavouriteArticlesEvent) {
        when (event) {

        }
    }

}