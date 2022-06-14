package com.example.geekbrainstranslator.data.converters

import androidx.room.TypeConverter
import java.util.stream.Collectors

class StringListConverter {
    @TypeConverter
    fun fromStringList(urlList: List<String>): String = urlList.stream().collect(Collectors.joining(", "))

    @TypeConverter
    fun toStringList(url: String): List<String> = url.split(", ")
}