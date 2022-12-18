package com.example.thindie.memogame.domain.useCases

import com.example.thindie.memogame.domain.MemoRepository
import com.example.thindie.memogame.domain.entities.GameResult

class CheckRecordUseCase(private val memoRepository: MemoRepository) {
    fun checkRecord(gameResult: GameResult) : Boolean {
        return memoRepository.checkRecord(gameResult)
    }
}
