package com.example.thindie.memogame.domain.useCases

import com.example.thindie.memogame.domain.MemoRepository
import com.example.thindie.memogame.domain.entities.GameWinners

class GetWinnersUseCase(private val memoRepository: MemoRepository) {
    fun getWinners(): GameWinners {
        return memoRepository.getWinners()
    }
}
