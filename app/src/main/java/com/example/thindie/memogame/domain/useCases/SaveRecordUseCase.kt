package com.example.thindie.memogame.domain.useCases

import com.example.thindie.memogame.domain.MemoRepository
import com.example.thindie.memogame.domain.entities.GameRecord
import com.example.thindie.memogame.domain.entities.GameResult

class SaveRecordUseCase(private val memoRepository: MemoRepository) {
    fun saveRecord(gameResult: GameResult)  {
        return memoRepository.saveRecord(gameResult)
    }
}
