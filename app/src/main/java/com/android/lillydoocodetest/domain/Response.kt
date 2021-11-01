package com.android.lillydoocodetest.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response(
    var error: Boolean,
    var amount: Int,
    var jokes: List<Jokes>
) : Parcelable