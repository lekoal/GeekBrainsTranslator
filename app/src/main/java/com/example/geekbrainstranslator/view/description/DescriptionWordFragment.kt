package com.example.geekbrainstranslator.view.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.geekbrainstranslator.R

class DescriptionWordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_description_word, container, false)
    }

    companion object {
        fun newInstance() = DescriptionWordFragment()
    }
}