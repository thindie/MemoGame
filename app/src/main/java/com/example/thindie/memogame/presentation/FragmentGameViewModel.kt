package com.example.thindie.memogame.presentation

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thindie.memogame.data.repository.MemoRepositoryImpl
import com.example.thindie.memogame.domain.entities.GameResult
import com.example.thindie.memogame.domain.entities.GameSettings
import com.example.thindie.memogame.domain.useCases.CollectScoreUseCase
import com.example.thindie.memogame.domain.useCases.GenerateQuestionUseCase
import com.example.thindie.memogame.domain.useCases.SaveRecordUseCase

class FragmentGameViewModel : ViewModel() {
    private val repository = MemoRepositoryImpl()
    private var gameSettings = GameSettings()
    private val collectScoreUseCase = CollectScoreUseCase(repository)
    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val saveRecordUseCase = SaveRecordUseCase(repository)

    private var timing: CountDownTimer? = null

    private var timer: Int = gameSettings.time

    private val _question = MutableLiveData<MutableList<Int>>()
    val question: LiveData<MutableList<Int>>
        get() = _question

    private val _time = MutableLiveData<String>()
    val time: LiveData<String>
        get() = _time

    private val _waitTime = MutableLiveData<Int>()
    val waitTime: LiveData<Int>
        get() = _waitTime

    private val _showTime = MutableLiveData<Int>()
    val showTime: LiveData<Int>
        get() = _showTime

    private val _score = MutableLiveData<String>()
    val score: LiveData<String>
        get() = _score


    private val _recordResult = MutableLiveData<GameResult>()
    val recordResult: LiveData<GameResult>
        get() = _recordResult

    private val _noRecordResult = MutableLiveData<GameResult>()
    val noRecordResult: LiveData<GameResult>
        get() = _noRecordResult

    private val _answersNeeded = MutableLiveData<Int>()
    val  answersNeeded: LiveData<Int>
        get() = _answersNeeded

    init {
        startGame()
        setTimer()
    }

    fun collectScore() {
        gameSettings.time = timer
        val gameSettings = collectScoreUseCase.collectScore(gameSettings)
        this@FragmentGameViewModel.gameSettings = gameSettings
        _score.value = this@FragmentGameViewModel.gameSettings.score.toString()
    }

    fun isARecordGame() {
        val gameResult = GameResult(
            gameSettings.score,
            timer
        )
        if (saveRecordUseCase.saveRecord(gameResult)) {
            _recordResult.value = gameResult
        } else {
            _noRecordResult.value = gameResult
        }

    }



    private fun startGame() {
        askQuestion()
    }

    fun askQuestion() {
        _answersNeeded.value = gameSettings.rightAnswers
        _waitTime.value = gameSettings.waitTime
        _showTime.value = gameSettings.showTime
        val question = generateQuestionUseCase.generateQuestion(gameSettings)
        _question.value = question.listOfColors
    }

    private fun setTimer() {
        timing = object : CountDownTimer(GAME_TIME * TIMER_TICK, TIMER_TICK) {

            override fun onTick(millisUntilFinished: Long) {
                timer++
                _time.value = formatTime(millisUntilFinished / TIMER_TICK)
            }

            override fun onFinish() {

            }
        }.start()
    }

    private fun formatTime(l: Long): String {
        val seconds = 60
        val minutes = l / seconds
        val seconds_ = l - minutes
        return String.format("%02d:%02d", minutes, seconds_)
    }

    companion object {
        private const val TIMER_TICK = 1000L
        private const val GAME_TIME = 100
    }
}