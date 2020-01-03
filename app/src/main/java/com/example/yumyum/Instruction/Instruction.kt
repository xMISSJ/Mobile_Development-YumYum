package com.example.yumyum.Instruction

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Instruction (
    var step: String,
    var instruction: String
): Parcelable