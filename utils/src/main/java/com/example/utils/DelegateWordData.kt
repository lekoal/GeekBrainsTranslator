package com.example.utils

import com.example.data.entity.db.WordData
import kotlin.reflect.KProperty

class DelegateWordData {
    private var currentData: WordData? = null
    operator fun setValue(thisRef: Any, property: KProperty<*>, data: WordData?) {
        currentData = data
    }

    operator fun getValue(thisRef: Any, property: KProperty<*>): WordData? {
        return currentData
    }
}