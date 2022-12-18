package com.example.thindie.memogame.data.mapper

import com.example.thindie.memogame.data.databBase.ScoreDbModel
import com.example.thindie.memogame.domain.entities.GameRecord

class Mapper {
    fun scoreDBtoScore(scoreDbModel: ScoreDbModel): GameRecord {
        return GameRecord(
            scoreDbModel.winnerName,
            scoreDbModel.score
        )
    }

    fun recordToScoreDB(gameRecord: GameRecord): ScoreDbModel {
        return ScoreDbModel(
            gameRecord.winnerName,
            gameRecord.score
        )
    }

}