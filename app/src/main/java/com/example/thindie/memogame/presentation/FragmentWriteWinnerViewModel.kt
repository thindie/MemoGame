package com.example.thindie.memogame.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thindie.memogame.data.repository.MemoRepositoryImpl
import com.example.thindie.memogame.domain.entities.GameRecord
import com.example.thindie.memogame.domain.entities.GameResult
import com.example.thindie.memogame.domain.useCases.AddScoreUseCase

class FragmentWriteWinnerViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MemoRepositoryImpl(application)
    private val addScore = AddScoreUseCase(repository)
    private lateinit var gameResult: GameResult

    private var _done = MutableLiveData<Unit>()
    val done: LiveData<Unit>
        get() = _done

    fun sendRecordName(name: String) {
        val gameRecord = GameRecord(
            name,
            gameResult.score
        )
         addScore.addScore(gameRecord)
        _done.value = Unit
    }

    fun setGameResult(gameResult: GameResult) {
        this@FragmentWriteWinnerViewModel.gameResult = gameResult
    }
}