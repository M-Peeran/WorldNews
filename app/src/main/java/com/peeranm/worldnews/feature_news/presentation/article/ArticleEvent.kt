package com.peeranm.worldnews.feature_news.presentation.article

import com.peeranm.worldnews.feature_news.model.Article

sealed class ArticleEvent {
    class SaveArticle(val article: Article): ArticleEvent()
}