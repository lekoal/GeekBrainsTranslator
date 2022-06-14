package com.example.geekbrainstranslator.view.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.geekbrainstranslator.R
import com.example.geekbrainstranslator.databinding.FragmentFavoriteWordBinding

class FavoriteWordFragment : Fragment() {

    private var _binding:FragmentFavoriteWordBinding? = null
    private val binding get() = _binding!!

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

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}