package com.example.thindie.memogame.data.repository

import android.graphics.Color
import com.example.thindie.memogame.domain.MemoRepository
import com.example.thindie.memogame.domain.entities.GameQuestion
import com.example.thindie.memogame.domain.entities.GameRecord
import com.example.thindie.memogame.domain.entities.GameResult
import com.example.thindie.memogame.domain.entities.GameSettings

class MemoRepositoryImpl : MemoRepository {

    override fun generateQuestion(): GameQuestion {
        val colorCan: MutableList<Int> = mutableListOf()
        val question = GameQuestion(colorCan)
        for (i in 0 until question.size) {
            question.listOfColors.add(Color.BLUE)
        }
        question.listOfColors.add(Color.CYAN)
        question.listOfColors.add(Color.CYAN)
        question.listOfColors.shuffle()
        return question
    }

    override fun saveRecord(gameResult: GameResult): GameRecord {
        TODO("Not yet implemented")
    }

    override fun collectScore(gameSettings: GameSettings): GameSettings {
        TODO("Not yet implemented")
    }
}