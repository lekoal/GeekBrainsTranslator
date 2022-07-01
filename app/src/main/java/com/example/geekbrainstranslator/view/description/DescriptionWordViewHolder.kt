package com.example.geekbrainstranslator.view.description

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.entity.db.WordDataDetails
import com.example.geekbrainstranslator.R

class DescriptionWordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val image: ImageView = itemView.findViewById(R.id.entity_image)
    private val transcription: TextView = itemView.findViewById(R.id.entity_transcription)
    private val translation: TextView = itemView.findViewById(R.id.entity_translation)
    private val partOfSpeech: TextView = itemView.findViewById(R.id.entity_part_of_speech_code)

    private val blurEffect = RenderEffect.createBlurEffect(16f, 16f, Shader.TileMode.MIRROR)

    fun bind(item: WordDataDetails) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            imageBlur()
        }
        Glide.with(itemView)
            .load("https:${item.imageUrl}")
            .into(image)
        transcription.text = item.transcription
        translation.text = item.translation
        partOfSpeech.text = item.partOfSpeechCode
    }

    @RequiresApi(31)
    private fun imageBlur() {
        image.setRenderEffect(blurEffect)
    }
}