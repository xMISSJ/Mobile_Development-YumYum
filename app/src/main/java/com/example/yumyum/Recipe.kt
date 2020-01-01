package com.example.yumyum

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe (

    // Card information.
    var name: String?,
    var image: Uri?,
    var servings: Int?,
    var preparationTime: Int?,
    var favorite: Boolean?,

    // Details screen information.
    var ingredients: List<String>?,
    var instructions: List<String>?
) : Parcelable