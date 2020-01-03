package com.example.yumyum.Recipe

import android.net.Uri
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "recipe_table")
data class Recipe (

    // Card information.
    var name: String?,
    var image: Uri?,
    var servings: Int?,
    var preparationTime: Int?,

    // Details screen information.
    var ingredients: ArrayList<String>?,
    var instructions: ArrayList<String>?,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

) : Parcelable