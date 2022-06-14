package com.example.geekbrainstranslator.view.description

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.geekbrainstranslator.R
import com.example.geekbrainstranslator.data.entity.db.WordData

class DescriptionWordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val image: ImageView = itemView.findViewById(R.id.entity_image)
    private val transcription: TextView = itemView.findViewById(R.id.entity_transcription)
    private val translation: TextView = itemView.findViewById(R.id.entity_translation)
    private val partOfSpeech: TextView = itemView.findViewById(R.id.entity_part_of_speech_code)

    fun bind(item: WordData) {
        Glide.with(itemView)
            .load(item.imageUrl)
            .into(image)

        transcription.text = item.transcription.toString()
        translation.text = item.translation.toString()
        partOfSpeech.text = item.partOfSpeechCode.toString()
    }
}