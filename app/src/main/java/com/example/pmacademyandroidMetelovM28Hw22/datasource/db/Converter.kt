package com.example.pmacademyandroidMetelovM28Hw22.datasource.db

import androidx.room.TypeConverter
import com.example.pmacademyandroidMetelovM28Hw22.datasource.model.AddedFrom

class Converter {

    @TypeConverter
    fun intToAddedFrom(value: Int) = enumValues<AddedFrom>()[value]

    @TypeConverter
    fun addedFromToInt(value: AddedFrom) = value.ordinal
}
