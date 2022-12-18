package com.example.thindie.memogame.data.databBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "winners")
data class ScoreDbModel (
    val winnerName : String?,
    @PrimaryKey
    val score : Int
)