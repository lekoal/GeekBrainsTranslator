package com.example.geekbrainstranslator.view.story

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.geekbrainstranslator.R
import com.example.geekbrainstranslator.data.entity.db.WordData

class SearchStoryRvAdapter : RecyclerView.Adapter<SearchStoryViewHolder>() {
    private val data = mutableListOf<WordData>()

    private fun setData(currentData: List<WordData>) {
        data.clear()
        currentData.forEach { wordData ->
            data.add(wordData)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchStoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_translation_rv_item, parent, false)
        return SearchStoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchStoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): WordData = data[position]

    override fun getItemCount(): Int = data.size

}