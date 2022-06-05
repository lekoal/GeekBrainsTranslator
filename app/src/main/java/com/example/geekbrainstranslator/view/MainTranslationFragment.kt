package com.example.geekbrainstranslator.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geekbrainstranslator.R
import com.example.geekbrainstranslator.app
import com.example.geekbrainstranslator.data.entity.TranslateDTO
import com.example.geekbrainstranslator.databinding.FragmentMainTranslationBinding

class MainTranslationFragment : Fragment(R.layout.fragment_main_translation),
    MainTranslationContract.View {

    private var _binding: FragmentMainTranslationBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: MainTranslationPresenter

    private lateinit var adapter: MainTranslationRvAdapter

    companion object {
        fun newInstance() = MainTranslationFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainTranslationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPrAd()
        initRv()
        onIconClick()
    }

    private fun onIconClick() {
        binding.inputTextLayout.setEndIconOnClickListener {
            requireActivity().hideKeyboard()
            if (binding.inputText.text.toString() != "") {
                presenter.getData(binding.inputText.text.toString())
            } else {
                setSearchError("Введите слово для поиска!")
            }
        }
    }

    override fun setSearchSuccess(data: List<TranslateDTO>) {
//        presenter.result.observe(requireActivity()) {
//            adapter.setData(it)
//        }
//        presenter.inProgress.observe(requireActivity()) {
//            binding.loadingProcessLayout.isVisible = it
//            binding.searchResultLayout.isVisible = !it
//            binding.mainTranslationFragmentLayout.isEnabled = !it
//        }
        adapter.setData(data)
    }

    override fun setProcessLoading(isLoading: Boolean) {
        binding.loadingProcessLayout.isVisible = isLoading
        binding.mainTranslationFragmentLayout.isEnabled = !isLoading
    }

    override fun setResultVisibility(isVisible: Boolean) {
        binding.searchResultLayout.isVisible = isVisible
    }

    override fun setSearchError(errorText: String) {
        Toast.makeText(requireContext(), errorText, Toast.LENGTH_SHORT).show()
    }

    private fun initPrAd() {
        presenter = app.presenter
        adapter = app.adapter
        presenter.viewAttach(this)
    }

    private fun initRv() {
        binding.rvResultList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvResultList.adapter = adapter
    }

    private fun Activity.hideKeyboard() {
        hideKeyboard(
            currentFocus ?: View(this)
        )
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = app.inputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            view.windowToken,
            0
        )
    }

    override fun onDestroyView() {
        _binding = null
        presenter.viewDetach(this)
        super.onDestroyView()
    }
}