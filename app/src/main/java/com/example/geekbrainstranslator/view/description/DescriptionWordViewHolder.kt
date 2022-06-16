package com.example.geekbrainstranslator.view.description

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.geekbrainstranslator.R
import com.example.geekbrainstranslator.data.entity.db.WordDataDetails

class DescriptionWordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val image: ImageView = itemView.findViewById(R.id.entity_image)
    private val transcription: TextView = itemView.findViewById(R.id.entity_transcription)
    private val translation: TextView = itemView.findViewById(R.id.entity_translation)
    private val partOfSpeech: TextView = itemView.findViewById(R.id.entity_part_of_speech_code)

    fun bind(item: WordDataDetails) {
        Glide.with(itemView)
            .load("https:${item.imageUrl}")
            .into(image)
        Log.i("MY_TAG", item.imageUrl.toString())
        transcription.text = item.transcription
        translation.text = item.translation
        partOfSpeech.text = item.partOfSpeechCode
    }
}