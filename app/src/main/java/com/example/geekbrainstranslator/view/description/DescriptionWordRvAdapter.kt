package com.example.geekbrainstranslator.view.description

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.entity.db.WordData
import com.example.data.entity.db.WordDataDetails
import com.example.geekbrainstranslator.R
import com.example.utils.WordDataConverter
import com.example.utils.WordDataConverterImpl

class DescriptionWordRvAdapter : RecyclerView.Adapter<DescriptionWordViewHolder>() {
    private var data: List<WordDataDetails>? = null

    private val converterImpl = WordDataConverterImpl()

    fun setData(currentData: WordData) {
        converterImpl.currentData = currentData
        data = DelegateConverter(converterImpl).convert()
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

    override fun getItemCount(): Int = data?.size!!

    private fun getItem(position: Int): WordDataDetails = data?.get(position)!!
}

class DelegateConverter(converter: WordDataConverter) : WordDataConverter by converter