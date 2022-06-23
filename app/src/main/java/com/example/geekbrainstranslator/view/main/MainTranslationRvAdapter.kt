package com.example.geekbrainstranslator.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.entity.web.TranslateDTO
import com.example.data.entity.web.Translation
import com.example.geekbrainstranslator.R

class MainTranslationRvAdapter : RecyclerView.Adapter<MainTranslationViewHolder>() {
    private val data = mutableListOf<Translation?>()

    fun setData(resultData: List<TranslateDTO>) {
        data.clear()
        resultData.forEach { translateDTO ->
            translateDTO.meanings?.forEach{ meaning ->
                data.add(meaning.translation)
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainTranslationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_translation_rv_item, parent, false)
        return MainTranslationViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainTranslationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): Translation? = data[position]

    override fun getItemCount(): Int = data.size
}