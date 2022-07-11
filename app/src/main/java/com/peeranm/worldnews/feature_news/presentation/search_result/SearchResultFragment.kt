package com.peeranm.worldnews.feature_news.presentation.search_result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.peeranm.worldnews.R
import com.peeranm.worldnews.core.collectWithLifecycle
import com.peeranm.worldnews.core.setActionbarTitle
import com.peeranm.worldnews.databinding.FragmentSearchResultBinding
import com.peeranm.worldnews.feature_news.model.Article
import com.peeranm.worldnews.feature_news.utils.ArticleAdapter
import com.peeranm.worldnews.feature_news.utils.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultFragment : Fragment(), OnItemClickListener<Article> {

    private var _binding: FragmentSearchResultBinding? = null
    private val binding: FragmentSearchResultBinding
    get() = _binding!!

    private val viewModel: SearchNewsViewModel by viewModels()

    private var adapter: ArticleAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchResultBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionbarTitle(R.string.search_results)

        adapter = ArticleAdapter(this)
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.listSearchResults.adapter = adapter
        binding.listSearchResults.layoutManager = layoutManager
        binding.listSearchResults.addItemDecoration(DividerItemDecoration(requireContext(), layoutManager.orientation))

        collectWithLifecycle(viewModel.searchResults) {
            adapter?.submitData(it)
        }

        viewModel.onEvent(SearchResultEvent.Search)
    }

    override fun onItemClick(view: View?, data: Article, position: Int) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}