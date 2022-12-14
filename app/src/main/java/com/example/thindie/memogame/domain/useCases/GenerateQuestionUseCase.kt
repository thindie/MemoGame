package com.example.thindie.memogame.domain.useCases

import com.example.thindie.memogame.domain.MemoRepository
import com.example.thindie.memogame.domain.entities.GameQuestion

class GenerateQuestionUseCase(private val memoRepository: MemoRepository) {
    fun generateQuestion( ) : GameQuestion {
        return memoRepository.generateQuestion()
    }
}