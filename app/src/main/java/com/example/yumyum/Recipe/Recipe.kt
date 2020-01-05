package com.example.yumyum.Recipe

import android.net.Uri
import android.os.Parcelable
import android.widget.ImageView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.yumyum.Ingredient.Ingredient
import com.example.yumyum.Instruction.Instruction
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
    var ingredients: ArrayList<Ingredient>?,
    var instructions: ArrayList<Instruction>?,

    var favorite: Boolean?,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

) : Parcelable