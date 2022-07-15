package com.peeranm.worldnews.feature_news.presentation.news_feed.news_country_dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.peeranm.worldnews.databinding.DialogNewsCategoryBinding

class NewsCategoryDialog : DialogFragment() {

    private var _binding: DialogNewsCategoryBinding? = null
    private val binding: DialogNewsCategoryBinding
    get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Choose country")
            .setView(getDialogView())
            .create()
    }

    private fun getDialogView(): View {
        _binding = DialogNewsCategoryBinding.inflate(layoutInflater)



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}