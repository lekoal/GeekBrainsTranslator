package com.example.geekbrainstranslator.view.story

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geekbrainstranslator.R
import com.example.geekbrainstranslator.databinding.FragmentSearchStoryWordBinding
import com.example.geekbrainstranslator.view.story.viewmodel.SearchHistoryViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class SearchStoryWordFragment : Fragment(), SearchStoryContract.View {

    private var _binding: FragmentSearchStoryWordBinding? = null
    private val binding get() = _binding!!

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
                    Toast.makeText(requireContext(), "HOME", Toast.LENGTH_SHORT).show()
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
            }
        }
    }

    private fun setAdapterClicker() {
        adapter.setOnItemClickListener(object : SearchStoryRvAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(requireContext(), "Clicked on $position", Toast.LENGTH_SHORT).show()
            }

            override fun onLongItemClick(position: Int) {
                Toast.makeText(requireContext(), "Long Clicked on $position", Toast.LENGTH_SHORT).show()
            }
        })

    }
}