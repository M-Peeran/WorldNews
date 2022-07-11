package com.peeranm.worldnews.feature_news.presentation.news_feed

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.peeranm.worldnews.R
import com.peeranm.worldnews.core.collectWithLifecycle
import com.peeranm.worldnews.core.setActionbarTitle
import com.peeranm.worldnews.databinding.FragmentNewsBinding
import com.peeranm.worldnews.feature_news.model.Article
import com.peeranm.worldnews.feature_news.utils.ArticleAdapter
import com.peeranm.worldnews.feature_news.utils.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment(), OnItemClickListener<Article>, SearchView.OnQueryTextListener {

    private val viewModel: NewsViewModel by viewModels()

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding
    get() = _binding!!

    private var adapter: ArticleAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setActionbarTitle(R.string.headlines)

        adapter = ArticleAdapter(this)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.listNews.adapter = adapter
        binding.listNews.layoutManager = layoutManager
        binding.listNews.addItemDecoration(DividerItemDecoration(requireContext(), layoutManager.orientation))

        collectWithLifecycle(viewModel.articles) {
            adapter?.submitData(it)
        }

        viewModel.onEvent(NewsFeedEvent.FetchHeadlines)
    }

    override fun onItemClick(view: View?, data: Article, position: Int) {
        findNavController().navigate(
            NewsFragmentDirections.actionNewsFragmentToArticleFragment(
                articleUrl = data.url,
                articleId = data.id
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        val actionSearch = menu.findItem(R.id.actionSearch).actionView as SearchView
        actionSearch.setOnQueryTextListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionChangeCountry -> {}
        }
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrBlank()) {
            findNavController().navigate(
                NewsFragmentDirections.actionNewsFragmentToSearchResultFragment(query)
            )
        }
        return false
    }
    override fun onQueryTextChange(newText: String?) = false

    override fun onDestroyView() {
        super.onDestroyView()
        adapter?.onClear()
        adapter = null
        _binding = null
    }

}