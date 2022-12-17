package com.example.thindie.memogame.domain.entities

data class GameSettings(
    var time: Int = 0,
    var score: Int = 0,
    var showTime: Int = INITIAL_TIME_PARAMETERS,
    var waitTime: Int = INITIAL_TIME_PARAMETERS
) {
    companion object
    {
        private const val INITIAL_TIME_PARAMETERS = 4

    }
}