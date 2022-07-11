package com.peeranm.worldnews.feature_news.use_cases

class ArticleUseCases(
    val getHeadlines: GetHeadlinesUseCase,
    val searchNews: SearchNewsUseCase,
    val getArticle: GetArticleUseCase,
    val insertFavArticle: InsertFavArticleUseCase,
    val getFavArticle: GetFavArticleUseCase,
    val deleteFavArticle: DeleteFavArticleUseCase,
    val getFavArticles: GetFavArticlesUseCase
)