package com.example.yumyum.Ingredient

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ingredient (
    var numerationCharacter: String,
    var ingredient: String
) : Parcelable