package com.example.thindie.memogame.data.repository

import android.graphics.Color
import com.example.thindie.memogame.domain.MemoRepository
import com.example.thindie.memogame.domain.entities.GameQuestion
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

    override fun saveRecord(gameResult: GameResult): Boolean {


        return false
    }

    override fun collectScore(gameSettings: GameSettings): GameSettings {
        val operation = gameSettings.copy()
        with(operation) {
            when (time / 10) {
                0 -> score += 50
                1 -> {
                    score += 100; showTime = 4
                }
                2 -> {
                    score += 100; showTime = 3
                }
                3 -> {
                    score += 100; waitTime = 3
                }
                4 -> {
                    score += 200; showTime = 2
                }
                5 -> {
                    score += 300; waitTime = 2
                }
                else -> {
                    score += 400; showTime = 1
                }
            }
        }
        return operation
    }
}