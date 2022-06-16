package com.example.geekbrainstranslator.view.main

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geekbrainstranslator.R
import com.example.geekbrainstranslator.app
import com.example.geekbrainstranslator.databinding.FragmentMainTranslationBinding
import com.example.geekbrainstranslator.view.description.DescriptionWordFragment
import com.example.geekbrainstranslator.view.main.viewmodel.MainTranslationViewModel
import com.example.geekbrainstranslator.view.story.SearchStoryWordFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainTranslationFragment : Fragment(R.layout.fragment_main_translation),
    MainTranslationContract.ViewViewModel {

    private var _binding: FragmentMainTranslationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainTranslationViewModel by viewModel(
        named("main_view_model")
    )

    private val adapter: MainTranslationRvAdapter by inject(
        named("main_adapter")
    )

    companion object {
        fun newInstance() = MainTranslationFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainTranslationBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainActionBar.inflateMenu(R.menu.main_menu)
        viewModel.onRestore()
        restoreView()
        initRv()
        onIconClick()
        toolbarMenuClicker()
    }

    private fun toolbarMenuClicker() {
        binding.mainActionBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.main_favorite_fragment -> {
                    Toast.makeText(requireContext(), "FAVORITE", Toast.LENGTH_SHORT).show()
                }
                R.id.main_history_fragment -> {
                    parentFragmentManager
                        .beginTransaction()
                        .replace(
                            R.id.main_activity_container,
                            SearchStoryWordFragment.newInstance()
                        )
                        .addToBackStack(null)
                        .commit()
                }
                R.id.search_in_history -> {
                    showSearchDialog()
                }
            }
            true
        }
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
            viewModel.sendDataToDB(it)
        }
        viewModel.onError.observe(viewLifecycleOwner) {
            if (it != null) {
                setSearchError(it.message.toString())
            }
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

    private fun showHistoryWordDetails() {
        viewModel.foundedData.observe(viewLifecycleOwner) {
            parentFragmentManager
                .beginTransaction()
                .replace(
                    R.id.main_activity_container,
                    DescriptionWordFragment.newInstance(it)
                )
                .addToBackStack(null)
                .commit()
        }
    }

    private fun showSearchDialog(): String {
        val builder = AlertDialog.Builder(requireContext())
        val dialogLayout = layoutInflater.inflate(
            R.layout.popup_edit_text_layout,
            null
        )
        var searchText = ""
        val editText = dialogLayout.findViewById<EditText>(R.id.search_in_history_edit_text)
        with(builder) {
            setTitle("Введите слово для поиска")
            setPositiveButton("Искать") { _, _ ->
                searchText = editText.text.toString()
                if (searchText != "") {
                    viewModel.searchInHistory(searchText)
                    viewModel.isInHistory.observe(viewLifecycleOwner) { inHistory ->
                        if (inHistory) {
                            showHistoryWordDetails()
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Не найдено",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }
            }
            setNegativeButton("Отмена") { _, _ ->
                Toast.makeText(requireContext(), "Поиск отменён", Toast.LENGTH_SHORT).show()
            }
            setView(dialogLayout)
            show()
        }
        return searchText
    }
}