package com.example.yumyum.Room

import android.net.Uri
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


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
    fun restoreList(listOfString: String?): ArrayList<String?>? {
        return Gson().fromJson<ArrayList<String?>>(
            listOfString,
            object : TypeToken<List<String?>?>() {}.type
        )
    }

    @TypeConverter
    fun saveList(listOfString: ArrayList<String?>?): String? {
        return Gson().toJson(listOfString)
    }
}