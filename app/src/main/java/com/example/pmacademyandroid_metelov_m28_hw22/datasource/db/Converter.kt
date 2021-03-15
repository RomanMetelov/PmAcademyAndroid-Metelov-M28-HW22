package com.example.pmacademyandroid_metelov_m28_hw22.datasource.db

import androidx.room.TypeConverter
import com.example.pmacademyandroid_metelov_m28_hw22.datasource.model.AddedFrom

class Converter {

    @TypeConverter
    fun intToAddedFrom(value: Int) = enumValues<AddedFrom>()[value]

    @TypeConverter
    fun addedFromToInt(value: AddedFrom) = value.ordinal
}