package com.example.thindie.memogame.domain.entities

data class GameSettings (
    var time : Int = 0,
    var score : Int = 0,
    var showTime : Int = 4,
    var waitTime : Int = 4
)