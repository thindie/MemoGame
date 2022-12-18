package com.example.thindie.memogame.domain.useCases

import com.example.thindie.memogame.domain.MemoRepository
import com.example.thindie.memogame.domain.entities.GameRecord

class AddScoreUseCase(private val memoRepository: MemoRepository) {
    fun addScore(gameRecord: GameRecord) {
        memoRepository.addScore(gameRecord)
    }
}