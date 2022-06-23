package com.example.geekbrainstranslator.view.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.entity.web.Translation
import com.example.geekbrainstranslator.R

class MainTranslationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val searchResultItem: TextView = itemView.findViewById(R.id.main_translation_rv_item_text)

    fun bind(item: Translation?) {
        searchResultItem.text = item?.text
    }
}