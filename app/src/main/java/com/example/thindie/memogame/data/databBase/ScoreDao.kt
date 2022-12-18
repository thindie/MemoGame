package com.example.thindie.memogame.data.databBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ScoreDao {
    @Query("SELECT * FROM winners ORDER BY score DESC")
    fun getScoreList():List<ScoreDbModel>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScoreRecord(scoreList : List<ScoreDbModel>)
}