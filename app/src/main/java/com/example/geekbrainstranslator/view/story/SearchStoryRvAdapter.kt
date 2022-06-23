package com.example.geekbrainstranslator.view.story

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.entity.db.WordData
import com.example.geekbrainstranslator.R

class SearchStoryRvAdapter : RecyclerView.Adapter<SearchStoryRvAdapter.SearchStoryViewHolder>() {
    private val data = mutableListOf<WordData>()

    private lateinit var mListener: OnItemClickListener

    fun setData(currentData: List<WordData>) {
        data.clear()
        currentData.forEach { wordData ->
            data.add(wordData)
            notifyDataSetChanged()
        }
    }

    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    fun removeFromHistory(wordData: WordData) {
        data.remove(wordData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchStoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_translation_rv_item, parent, false)
        return SearchStoryViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: SearchStoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): WordData = data[position]

    override fun getItemCount(): Int = data.size

    inner class SearchStoryViewHolder(
        itemView: View,
        listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView) {
        private val storyItem: TextView = itemView.findViewById(R.id.main_translation_rv_item_text)

        fun bind(item: WordData) {
            storyItem.text = item.text
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(absoluteAdapterPosition)
            }
            itemView.setOnLongClickListener {
                listener.onLongItemClick(absoluteAdapterPosition)
                true
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onLongItemClick(position: Int)
    }
}