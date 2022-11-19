package com.example.splitthebill.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Integrantes (

    val id: Number,
    var name: String,
    var valuepay: Double,
    var listBuy: String,
) : Parcelable
