package com.example.geekbrainstranslator.view

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
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
import com.example.geekbrainstranslator.domain.RepositoryUsecase
import com.example.geekbrainstranslator.view.viewmodel.MainTranslationViewModel
import javax.inject.Inject

class MainTranslationFragment : Fragment(R.layout.fragment_main_translation),
    MainTranslationContract.ViewViewModel {

    private var _binding: FragmentMainTranslationBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainTranslationViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: MainTranslationRvAdapter

    @Inject
    lateinit var repoUsecase: RepositoryUsecase

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

        app.mainTranslationAppComponent.inject(this)

        viewModel = ViewModelProvider(
            viewModelStore,
            viewModelFactory
        )[
                MainTranslationViewModel::class.java
        ]

        viewModel.onRestore()
        restoreView()

        initRv()
        onIconClick()
    }

    private fun onIconClick() {
        binding.inputTextLayout.setEndIconOnClickListener {
            requireActivity().hideKeyboard()
            if (binding.inputText.text.toString() != "") {
                setSearchSuccess()
                if (isOnline(requireContext())) {
                    viewModel.onSearch(binding.inputText.text.toString())
                } else {
                    setSearchError("Отсутствует подключение!")
                }
            } else {
                setSearchError("Введите слово для поиска!")
            }
        }
    }

    override fun setSearchSuccess() {
        viewModel.result.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        viewModel.inProgress.observe(viewLifecycleOwner) {
            binding.loadingProcessLayout.isVisible = it
            binding.searchResultLayout.isVisible = !it
            binding.mainTranslationFragmentLayout.isEnabled = !it
        }
    }

    private fun restoreView() {
        viewModel.result.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.searchResultLayout.isVisible = true
                adapter.setData(it)
            }
        }
    }

    override fun setSearchError(errorText: String) {
        Toast.makeText(requireContext(), errorText, Toast.LENGTH_SHORT).show()
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

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
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