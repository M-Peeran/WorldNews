package com.peeranm.worldnews.feature_news.presentation.article

sealed class UiAction {
    object None : UiAction()
    class ShowMessage(val message: String) : UiAction()

}