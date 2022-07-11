package com.peeranm.worldnews.feature_news.presentation.search_result

sealed class SearchResultEvent {
    object Search : SearchResultEvent()
}