package com.example.geekbrainstranslator.view.favorite

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.geekbrainstranslator.R
import com.example.geekbrainstranslator.data.entity.db.WordData

class FavoriteWordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val favoriteItem: TextView = itemView.findViewById(R.id.main_translation_rv_item_text)

    fun bind(item: WordData) {
        favoriteItem.text = item.text
    }
}