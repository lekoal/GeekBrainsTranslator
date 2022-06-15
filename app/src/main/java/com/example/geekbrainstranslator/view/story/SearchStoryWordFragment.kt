package com.example.geekbrainstranslator.view.story

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.geekbrainstranslator.R
import com.example.geekbrainstranslator.databinding.FragmentSearchStoryWordBinding
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class SearchStoryWordFragment : Fragment(), SearchStoryContract.View {

    private var _binding: FragmentSearchStoryWordBinding? = null
    private val binding get() = _binding!!

    private val adapter: SearchStoryRvAdapter by inject(named("search_history_adapter"))

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

        toolbarMenuClicker()
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
            }
            true
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun setData() {
        
    }
}