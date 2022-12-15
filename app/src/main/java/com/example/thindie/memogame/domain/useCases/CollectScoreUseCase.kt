package com.example.thindie.memogame.domain.useCases

import com.example.thindie.memogame.domain.MemoRepository
import com.example.thindie.memogame.domain.entities.GameSettings

class CollectScoreUseCase(private val memoRepository: MemoRepository) {
    fun collectScore(gameSettings: GameSettings): GameSettings {
        return memoRepository.collectScore(gameSettings)
    }
}