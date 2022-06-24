package com.example.utils

import android.util.Log
import com.example.data.entity.db.WordDataDetails
import kotlin.reflect.KProperty

class DelegateWordDataDetails {
    private var data = mutableListOf<WordDataDetails>()

    operator fun setValue(thisRef: Any, property: KProperty<*>, currentData: List<WordDataDetails>) {
        data = currentData as MutableList<WordDataDetails>
    }

    operator fun getValue(thisRef: Any, property: KProperty<*>): List<WordDataDetails> {
        return data
    }
}