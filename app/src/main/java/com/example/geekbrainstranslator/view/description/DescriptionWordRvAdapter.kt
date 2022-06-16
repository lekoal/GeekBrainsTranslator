package com.example.geekbrainstranslator.view.description

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.geekbrainstranslator.R
import com.example.geekbrainstranslator.data.entity.db.WordData
import com.example.geekbrainstranslator.data.entity.db.WordDataDetails

class DescriptionWordRvAdapter : RecyclerView.Adapter<DescriptionWordViewHolder>() {
    private val data = mutableListOf<WordDataDetails>()

    fun setData(currentData: WordData) {
        data.clear()
        val translationList = currentData.translation
        val transcriptionList = currentData.transcription
        val imageUrlList = currentData.imageUrl
        val partOfSpeechList = currentData.partOfSpeechCode
        val size = currentData.translation.size

        for (i in 0 until size) {
            data.add(
                WordDataDetails(
                    imageUrl = imageUrlList[i],
                    translation = translationList[i],
                    transcription = transcriptionList[i],
                    partOfSpeechCode = partOfSpeechList[i]
                )
            )
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DescriptionWordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.word_details_rv_item, parent, false)
        return DescriptionWordViewHolder(view)
    }

    override fun onBindViewHolder(holder: DescriptionWordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = data.size

    private fun getItem(position: Int): WordDataDetails = data[position]
}