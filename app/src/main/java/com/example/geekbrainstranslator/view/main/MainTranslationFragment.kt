package com.example.geekbrainstranslator.view.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
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
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named

class MainTranslationFragment : Fragment(R.layout.fragment_main_translation),
    MainTranslationContract.ViewViewModel {

    private var _binding: FragmentMainTranslationBinding? = null
    private val binding get() = _binding!!

    private lateinit var connectionLiveData: ConnectionLiveData

    private var isConnected = true

    private val scope by lazy {
        getKoin().getOrCreateScope<MainTranslationFragment>(SCOPE_ID)
    }

    private val adapter: MainTranslationRvAdapter by lazy {
        scope.get(named("main_adapter"))
    }

    private val viewModel: MainTranslationViewModel by lazy {
        scope.get(named("main_view_model"))
    }

    companion object {
        fun newInstance() = MainTranslationFragment()
        const val SCOPE_ID = "mainScope"
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
        isOnline()
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
                if (isConnected) {
                    viewModel.onSearch(binding.inputText.text.toString())
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

    private fun isOnline() {
        val snackBar: Snackbar =
            Snackbar.make(binding.root, "Отсутствует подключение!", Snackbar.LENGTH_INDEFINITE)
        connectionLiveData = ConnectionLiveData(app)
        connectionLiveData.observe(viewLifecycleOwner) { isOnline ->
            isConnected = if (isOnline) {
                if (snackBar.isShownOrQueued) {
                    snackBar.dismiss()
                }
                true
            } else {
                snackBar.show()
                false
            }
        }
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
        scope.close()
        super.onDestroyView()
    }

    private fun showHistoryWordDetails(searchingText: String) {
        parentFragmentManager
            .beginTransaction()
            .replace(
                R.id.main_activity_container,
                DescriptionWordFragment.newInstance(searchingText)
            )
            .addToBackStack(null)
            .commit()
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
                            showHistoryWordDetails(searchText)
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