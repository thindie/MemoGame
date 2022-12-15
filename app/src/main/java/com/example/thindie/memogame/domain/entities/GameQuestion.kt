package com.example.thindie.memogame.domain.entities

data class GameQuestion(
    val listOfColors: MutableList<Int>,
    val size : Int = SIZE
) {
    companion object {
   private const val SIZE = 14
    }
}