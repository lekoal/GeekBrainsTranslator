package com.example.geekbrainstranslator.view.favorite

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.entity.db.WordData
import com.example.geekbrainstranslator.R

class FavoriteWordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val favoriteItem: TextView = itemView.findViewById(R.id.main_translation_rv_item_text)

    fun bind(item: WordData) {
        favoriteItem.text = item.text
    }
}