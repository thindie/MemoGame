package com.example.thindie.memogame.data.repository

import android.app.Application
import android.graphics.Color
import com.example.thindie.memogame.data.databBase.ScoreDataBase
import com.example.thindie.memogame.data.databBase.ScoreDbModel
import com.example.thindie.memogame.data.mapper.Mapper
import com.example.thindie.memogame.domain.MemoRepository
import com.example.thindie.memogame.domain.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemoRepositoryImpl(application: Application) : MemoRepository {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val mapper = Mapper()

    private val databaseInstance = ScoreDataBase.getInstance(application).scoreListDao()

    private val dataBaseList = databaseInstance.getScoreList()

    override fun generateQuestion(gameSettings: GameSettings): GameQuestion {
        val colorCan: MutableList<Int> = mutableListOf()
        val question = GameQuestion(colorCan)
        when (gameSettings.level) {
            Level.REGULAR -> {

                for (i in 0 until question.size) {
                    question.listOfColors.add(Color.BLUE)
                }
                question.listOfColors.add(Color.RED)
                question.listOfColors.add(Color.RED)
                question.listOfColors.shuffle()
                return question
            }
            Level.NORMAL -> {
                for (i in 0 until question.size - 1) {
                    question.listOfColors.add(Color.BLUE)
                }
                question.listOfColors.add(Color.RED)
                question.listOfColors.add(Color.RED)
                question.listOfColors.add(Color.RED)
                question.listOfColors.shuffle()
                return question
            }
            Level.HARD -> {
                for (i in 0 until question.size - 2) {
                    question.listOfColors.add(Color.BLUE)
                }
                question.listOfColors.add(Color.RED)
                question.listOfColors.add(Color.RED)
                question.listOfColors.add(Color.RED)
                question.listOfColors.add(Color.RED)
                question.listOfColors.shuffle()
                return question
            }
        }

    }

    override fun checkRecord(gameResult: GameResult): Boolean {
        if(gameResult.score < 600) return false
        if(dataBaseList.isEmpty()) return true
        if(dataBaseList.last().score > gameResult.score ) return false
        return true
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
                    score += 100; waitTime = 3; level = Level.NORMAL;
                    rightAnswers = GameSettings.LEVEL_NORMAL_ANSWERS
                }
                4 -> {
                    score += 200; showTime = 2
                }
                5 -> {
                    score += 300; waitTime = 2
                }
                else -> {
                    score += 400; showTime = 1; level = Level.HARD;
                    rightAnswers = GameSettings.LEVEL_HARD_ANSWERS
                }
            }
        }
        return operation
    }

   override fun addScore(gameRecord: GameRecord){
        val dbList: MutableList<ScoreDbModel> = dataBaseList as MutableList
        val dbModel = mapper.recordToScoreDB(gameRecord)
        dbList.add(dbModel)
        coroutineScope.launch {
            databaseInstance.insertScoreRecord(dbList)
        }
    }

    override fun getWinners() : GameWinners{
        val gameWinners = GameWinners(list = mutableListOf())
        val list = dataBaseList.map {
            mapper.scoreDBtoScore(it)
        }
        gameWinners.list.addAll(list)
        return gameWinners
    }


}