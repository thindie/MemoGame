package com.example.thindie.memogame.domain

import com.example.thindie.memogame.domain.entities.GameQuestion
import com.example.thindie.memogame.domain.entities.GameRecord
import com.example.thindie.memogame.domain.entities.GameResult

interface MemoRepository {
    fun generateQuestion(): GameQuestion
    fun saveRecord(gameResult: GameResult): GameRecord
}