package com.example.geekbrainstranslator.view.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.geekbrainstranslator.R
import com.example.geekbrainstranslator.databinding.FragmentDescriptionWordBinding
import com.example.geekbrainstranslator.databinding.FragmentFavoriteWordBinding

class DescriptionWordFragment : Fragment() {

    private var _binding: FragmentDescriptionWordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDescriptionWordBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        fun newInstance() = DescriptionWordFragment()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}