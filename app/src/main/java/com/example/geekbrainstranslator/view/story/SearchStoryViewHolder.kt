package com.example.geekbrainstranslator.view.story

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.geekbrainstranslator.R
import com.example.geekbrainstranslator.data.entity.db.WordData

class SearchStoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val storyItem: TextView = itemView.findViewById(R.id.main_translation_rv_item_text)

    fun bind(item: WordData) {
        storyItem.text = item.text
    }
}