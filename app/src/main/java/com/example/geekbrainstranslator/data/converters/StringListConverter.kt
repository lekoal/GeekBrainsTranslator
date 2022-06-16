package com.example.geekbrainstranslator.data.converters

import androidx.room.TypeConverter
import java.util.stream.Collectors

class StringListConverter {
    @TypeConverter
    fun toStringList(urlList: List<String>): String = urlList.stream().collect(Collectors.joining(", "))

    @TypeConverter
    fun fromStringList(url: String): List<String> = url.split(", ")
}