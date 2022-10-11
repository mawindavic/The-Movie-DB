package com.mawinda.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun List<Int>?.listIntsToString(): String = if (this == null) "" else Gson().toJson(this)

    @TypeConverter
    fun String.toListOfInts(): List<Int> = if (this.isEmpty()) listOf() else Gson().fromJson(
        this,
        object : TypeToken<List<Int>>() {}.type
    ) as List<Int>


    @TypeConverter
    fun List<String>?.listOfStringsToString(): String =
        if (this == null) "" else Gson().toJson(this)

    @TypeConverter
    fun String.toListOfStrings(): List<String> = if (this.isEmpty()) listOf() else Gson().fromJson(
        this,
        object : TypeToken<List<String>>() {}.type
    ) as List<String>
}


