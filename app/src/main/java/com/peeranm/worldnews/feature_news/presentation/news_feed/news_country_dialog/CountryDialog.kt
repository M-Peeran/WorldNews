package com.peeranm.worldnews.feature_news.presentation.news_feed.news_country_dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.peeranm.worldnews.databinding.DialogCountryBinding

class CountryDialog : DialogFragment() {

    private var _binding: DialogCountryBinding? = null
    private val binding: DialogCountryBinding
    get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Choose Country")
            .setView(getDialogView())
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.setCanceledOnTouchOutside(false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun getDialogView(): View {
        _binding = DialogCountryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}