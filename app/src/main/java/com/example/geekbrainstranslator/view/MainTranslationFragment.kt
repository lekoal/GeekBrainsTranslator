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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geekbrainstranslator.R
import com.example.geekbrainstranslator.app
import com.example.geekbrainstranslator.databinding.FragmentMainTranslationBinding

class MainTranslationFragment : Fragment(R.layout.fragment_main_translation),
    MainTranslationContract.ViewViewModel {

    private var _binding: FragmentMainTranslationBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this)[MainTranslationViewModel::class.java]
    }

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

        initAdapter()
        initRv()
        onIconClick()
    }

    private fun onIconClick() {
        binding.inputTextLayout.setEndIconOnClickListener {
            requireActivity().hideKeyboard()
            setSearchSuccess()
            if (binding.inputText.text.toString() != "") {
                viewModel.onSearch(binding.inputText.text.toString())
            } else {
                setSearchError("Введите слово для поиска!")
            }
        }
    }

    override fun setSearchSuccess() {
        viewModel.result.observe(requireActivity()) {
            adapter.setData(it)
        }
        viewModel.inProgress.observe(requireActivity()) {
            binding.loadingProcessLayout.isVisible = it
            binding.searchResultLayout.isVisible = !it
            binding.mainTranslationFragmentLayout.isEnabled = !it
        }
    }

    override fun setSearchError(errorText: String) {
        Toast.makeText(requireContext(), errorText, Toast.LENGTH_SHORT).show()
    }

    private fun initAdapter() {
        adapter = app.adapter
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
        super.onDestroyView()
    }
}