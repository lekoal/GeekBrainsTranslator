package com.example.geekbrainstranslator.view.story

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.geekbrainstranslator.R

class SearchStoryWordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_story_word, container, false)
    }

    companion object {
        fun newInstance() = SearchStoryWordFragment()
    }
}