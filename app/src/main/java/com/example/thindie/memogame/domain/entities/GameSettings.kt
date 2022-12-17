package com.example.thindie.memogame.domain.entities

data class GameSettings(
    var time: Int = 0,
    var score: Int = 0,
    var showTime: Int = INITIAL_TIME_PARAMETERS,
    var waitTime: Int = INITIAL_TIME_PARAMETERS,
    var level : Level = Level.REGULAR,
    var rightAnswers : Int = INITIAL_ANSWER_PARAMETERS
) {
    companion object
    {
        private const val INITIAL_TIME_PARAMETERS = 4
        private const val INITIAL_ANSWER_PARAMETERS = 2
        const val LEVEL_NORMAL_ANSWERS = 3
        const val LEVEL_HARD_ANSWERS = 4
    }
}