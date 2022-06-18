package com.example.geekbrainstranslator.view.story

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.entity.db.WordData
import com.example.geekbrainstranslator.R
import com.example.geekbrainstranslator.databinding.FragmentSearchStoryWordBinding
import com.example.geekbrainstranslator.view.description.DescriptionWordFragment
import com.example.geekbrainstranslator.view.main.MainTranslationFragment
import com.example.geekbrainstranslator.view.story.viewmodel.SearchHistoryViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class SearchStoryWordFragment : Fragment(), SearchStoryContract.View {

    private var _binding: FragmentSearchStoryWordBinding? = null
    private val binding get() = _binding!!

    private lateinit var listData: List<WordData>

    private val adapter: SearchStoryRvAdapter by inject(
        named("search_history_adapter")
    )

    private val viewModel: SearchHistoryViewModel by viewModel(
        named("search_history_view_model")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchStoryWordBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.history_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    companion object {
        fun newInstance() = SearchStoryWordFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.historyActionBar.inflateMenu(R.menu.history_menu)
        viewModel.getHistory()
        toolbarMenuClicker()
        initRv()
        setData()
        setAdapterClicker()
    }

    private fun toolbarMenuClicker() {
        binding.historyActionBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.history_favorite_fragment -> {
                    Toast.makeText(requireContext(), "FAVORITE", Toast.LENGTH_SHORT).show()
                }
                R.id.history_home_fragment -> {
                    parentFragmentManager
                        .beginTransaction()
                        .replace(
                            R.id.main_activity_container,
                            MainTranslationFragment.newInstance()
                        )
                        .commit()
                }
                R.id.history_clear -> {
                    viewModel.clearHistory()
                    adapter.clearData()
                }
            }
            true
        }
    }

    private fun initRv() {
        binding.rvHistoryList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHistoryList.adapter = adapter
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun setData() {
        viewModel.storyList.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                adapter.setData(it)
                listData = it
            }
        }
    }

    private fun setAdapterClicker() {
        adapter.setOnItemClickListener(object : SearchStoryRvAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                parentFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.main_activity_container,
                        DescriptionWordFragment.newInstance(listData[position].text.toString())
                    )
                    .addToBackStack(null)
                    .commit()
            }

            override fun onLongItemClick(position: Int) {
                showPopupMenu(position, binding.rvHistoryList[position])
            }
        })
    }

    private fun showPopupMenu(position: Int, anchor: View) {
        val popupMenu = PopupMenu(requireContext(), anchor)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.delete_history_item -> {
                    adapter.removeFromHistory(listData[position])
                    viewModel.deleteHistoryItem(listData[position])
                }
                R.id.close_menu -> {
                    popupMenu.dismiss()
                }
            }
            true
        }
    }
}