package com.example.geekbrainstranslator.view.favorite

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.geekbrainstranslator.R
import com.example.geekbrainstranslator.databinding.FragmentFavoriteWordBinding

class FavoriteWordFragment : Fragment() {

    private var _binding: FragmentFavoriteWordBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteWordBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        fun newInstance() = FavoriteWordFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favoriteActionBar.inflateMenu(R.menu.favorite_menu)

        toolbarMenuClicker()
    }

    private fun toolbarMenuClicker() {
        binding.favoriteActionBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.favorite_home_fragment -> {
                    Toast.makeText(requireContext(), "HOME", Toast.LENGTH_SHORT).show()
                }
                R.id.favorite_history_fragment -> {
                    Toast.makeText(requireContext(), "HISTORY", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}