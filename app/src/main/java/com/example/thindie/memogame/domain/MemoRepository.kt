package com.example.thindie.memogame.domain

import com.example.thindie.memogame.domain.entities.*

interface MemoRepository {
    fun generateQuestion(gameSettings: GameSettings): GameQuestion
    fun checkRecord(gameResult: GameResult): Boolean
    fun collectScore(gameSettings: GameSettings): GameSettings
    fun getWinners() : GameWinners
    fun addScore(gameRecord: GameRecord)

}