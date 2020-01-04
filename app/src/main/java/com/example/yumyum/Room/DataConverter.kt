package com.example.yumyum.Room

import android.net.Uri
import androidx.room.TypeConverter

class DataConverter {
    @TypeConverter
    fun fromString(value: String?): Uri? {
        return if (value == null) null else Uri.parse(value)
    }

    @TypeConverter
    fun toString(uri: Uri?): String? {
        return uri.toString()
    }

    @TypeConverter
    fun stringAsStringList(strings: String?): ArrayList<String> {
        val list = ArrayList<String>()
        strings
            ?.split(",")
            ?.forEach {
                list.add(it)
            }

        return list
    }

    @TypeConverter
    fun stringListAsString(strings: ArrayList<String>?): String {
        var result = ""
        strings?.forEach { element ->
            result += "$element,"
        }
        return result.removeSuffix(",")
    }
}