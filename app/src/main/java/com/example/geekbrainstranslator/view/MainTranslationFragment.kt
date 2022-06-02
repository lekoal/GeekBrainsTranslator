package com.example.geekbrainstranslator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geekbrainstranslator.R
import com.example.geekbrainstranslator.data.RepoUsecaseImpl
import com.example.geekbrainstranslator.databinding.FragmentMainTranslationBinding

class MainTranslationFragment : Fragment(R.layout.fragment_main_translation),
    MainTranslationContract.View {

    private var _binding: FragmentMainTranslationBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: MainTranslationPresenter

    private val repoUsecase = RepoUsecaseImpl()

    private val adapter = MainTranslationRvAdapter()

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

        initPresenter()
        initRv()
        onIconClick()
    }

    private fun onIconClick() {
        binding.inputTextLayout.setEndIconOnClickListener {
            setSearchSuccess()
            if (binding.inputText.text.toString() != "") {
                presenter.onSearch(binding.inputText.text.toString())
            } else {
                setSearchError("Введите слово для поиска!")
            }
        }
    }

    override fun setSearchSuccess() {
        presenter.result.observe(requireActivity()) {
            adapter.setData(it)
        }
        presenter.inProgress.observe(requireActivity()) {
            binding.loadingProcessLayout.isVisible = it
            binding.searchResultLayout.isVisible = !it
            binding.mainTranslationFragmentLayout.isEnabled = !it
        }
    }

    override fun setSearchError(errorText: String) {
        Toast.makeText(requireContext(), errorText, Toast.LENGTH_SHORT).show()
    }

    private fun initPresenter() {
        presenter = MainTranslationPresenter(repoUsecase)
        presenter.onViewAttach(this)
    }

    private fun initRv() {
        binding.rvResultList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvResultList.adapter = adapter
    }

    override fun onDestroy() {
        presenter.onViewDetach(this)
        super.onDestroy()
    }
}