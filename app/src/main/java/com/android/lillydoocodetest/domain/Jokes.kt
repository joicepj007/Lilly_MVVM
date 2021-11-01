package com.android.lillydoocodetest.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Jokes(
    val id: String,
    val category: String,
    val delivery: String,
    val setup: String,
    val joke: String?=null,
    val type: String,
    val safe: String,
    val lang: String
) :Parcelable