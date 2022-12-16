package com.example.thindie.memogame.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameResult (
    val score : Int,
    val time : Int,
) : Parcelable