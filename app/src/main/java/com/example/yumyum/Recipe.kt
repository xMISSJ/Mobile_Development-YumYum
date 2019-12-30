package com.example.yumyum

import android.net.Uri

data class Recipe (

    // Card information.
    var image: Uri?,
    var name: String,
    var servings: Int,
    var preparationTime: Int,
    var favorite: Boolean,

    // Details screen information.
    var ingredients: List<String>,
    var instructions: List<String>
)