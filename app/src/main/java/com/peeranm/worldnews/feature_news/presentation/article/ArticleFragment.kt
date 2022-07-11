package com.peeranm.worldnews.feature_news.presentation.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.peeranm.worldnews.core.collectWithLifecycle
import com.peeranm.worldnews.core.showToast
import com.peeranm.worldnews.databinding.FragmentArticleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding: FragmentArticleBinding
    get() = _binding!!

    private val viewModel: ArticleViewModel by viewModels()
    private val args: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadArticle()
        binding.handleOnFabFavouriteClick()

        collectWithLifecycle(viewModel.uiAction) { uiAction ->
            when (uiAction) {
                is UiAction.ShowMessage -> showToast(uiAction.message)
                is UiAction.HideFabFavouriteArticle -> binding.hideFabFavouriteArticle()
                is UiAction.None -> Unit
            }
        }
    }

    private fun FragmentArticleBinding.hideFabFavouriteArticle() {
        fabFavouriteArticle.visibility = View.GONE
    }

    private fun FragmentArticleBinding.loadArticle() {
        if (args.articleUrl.isEmpty()) {
            showToast("Invalid URL!")
            return
        }
        webviewArticle.webViewClient = WebViewClient()
        webviewArticle.loadUrl(args.articleUrl)
    }

    private fun FragmentArticleBinding.handleOnFabFavouriteClick() {
        fabFavouriteArticle.setOnClickListener {
            viewModel.onEvent(ArticleEvent.SaveArticle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}