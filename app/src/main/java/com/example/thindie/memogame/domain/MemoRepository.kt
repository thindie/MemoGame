package com.example.thindie.memogame.domain

import com.example.thindie.memogame.domain.entities.GameQuestion
import com.example.thindie.memogame.domain.entities.GameRecord
import com.example.thindie.memogame.domain.entities.GameResult
import com.example.thindie.memogame.domain.entities.GameSettings

interface MemoRepository {
    fun generateQuestion(): GameQuestion
    fun saveRecord(gameResult: GameResult)
    fun collectScore(gameSettings: GameSettings): GameSettings
}