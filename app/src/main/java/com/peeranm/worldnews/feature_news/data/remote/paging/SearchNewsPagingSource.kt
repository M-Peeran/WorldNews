package com.peeranm.worldnews.feature_news.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.peeranm.worldnews.core.Constants
import com.peeranm.worldnews.feature_news.data.remote.RetrofitInstance
import com.peeranm.worldnews.feature_news.data.remote.dto.ArticleDto
import com.peeranm.worldnews.feature_news.model.Article
import com.peeranm.worldnews.feature_news.utils.ArticleMapper

class SearchNewsPagingSource(
    private val searchQuery: String,
    private val retrofitInstance: RetrofitInstance,
    private val mapper: ArticleMapper
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val currentPage = params.key ?: Constants.INITIAL_PAGE

            val response = retrofitInstance.newsAPI.searchForNews(
                query = searchQuery,
                page = currentPage,
                pageSize = params.loadSize
            )

            if (response.isSuccessful) {
                val newsResponse = response.body()
                newsResponse?.let {
                    return LoadResult.Page(
                        data = newsResponse.articles.map { mapper.fromDtoToUiModel(it) },
                        prevKey = if (currentPage == Constants.INITIAL_PAGE) null else currentPage - 1,
                        nextKey = if (newsResponse.articles.isEmpty()) null else currentPage + 1
                    )
                }
            }
            LoadResult.Error(Exception("Received failed response"))
        } catch (exception: Exception) {LoadResult.Error(exception) }
    }
}