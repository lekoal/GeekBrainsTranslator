package com.example.geekbrainstranslator.view.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.geekbrainstranslator.R
import com.example.geekbrainstranslator.data.entity.web.Translation

class MainTranslationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val searchResultItem: TextView = itemView.findViewById(R.id.main_translation_rv_item_text)

    fun bind(item: Translation?) {
        searchResultItem.text = item?.text
    }
}