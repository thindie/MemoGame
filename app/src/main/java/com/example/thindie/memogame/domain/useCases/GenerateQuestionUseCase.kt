package com.example.thindie.memogame.domain.useCases

import com.example.thindie.memogame.domain.MemoRepository
import com.example.thindie.memogame.domain.entities.GameQuestion
import com.example.thindie.memogame.domain.entities.GameSettings

class GenerateQuestionUseCase(private val memoRepository: MemoRepository) {
    fun generateQuestion(gameSettings: GameSettings) : GameQuestion {
        return memoRepository.generateQuestion(gameSettings )
    }
}