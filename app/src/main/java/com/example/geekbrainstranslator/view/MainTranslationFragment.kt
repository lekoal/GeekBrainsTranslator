package com.example.geekbrainstranslator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.geekbrainstranslator.R
import com.example.geekbrainstranslator.databinding.FragmentMainTranslationBinding

class MainTranslationFragment : Fragment(R.layout.fragment_main_translation) {

    private var _binding: FragmentMainTranslationBinding? = null
    private val binding get() = _binding!!

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

}