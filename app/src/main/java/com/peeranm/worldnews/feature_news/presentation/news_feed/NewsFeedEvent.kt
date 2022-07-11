package com.peeranm.worldnews.feature_news.presentation.news_feed

sealed class NewsFeedEvent {
    object FetchHeadlines : NewsFeedEvent()
}