package com.example.thindie.memogame.data.repository

import com.example.thindie.memogame.domain.MemoRepository
import com.example.thindie.memogame.domain.entities.GameQuestion
import com.example.thindie.memogame.domain.entities.GameRecord
import com.example.thindie.memogame.domain.entities.GameResult

class MemoRepositoryImpl : MemoRepository {
    override fun generateQuestion(): GameQuestion {
        TODO("Not yet implemented")
    }

    override fun saveRecord(gameResult: GameResult): GameRecord {
        TODO("Not yet implemented")
    }
}