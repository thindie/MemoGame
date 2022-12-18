package com.example.thindie.memogame.data.databBase

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [ScoreDbModel::class], exportSchema = false)
abstract class ScoreDataBase : RoomDatabase() {

    abstract fun scoreListDao(): ScoreDao

    companion object {
        private var INSTANCE: ScoreDataBase? = null
        private val LOCK = Any()
        private const val DB_NAME = "scoreDb"
        fun getInstance(application: Application): ScoreDataBase{
            INSTANCE?.let{
                return it
            }
            synchronized(LOCK){
                INSTANCE?.let { return it }
            }
            val dataBase = Room.databaseBuilder(
                application,
                ScoreDataBase::class.java,
                DB_NAME
            ).build()
            INSTANCE = dataBase
            return INSTANCE as ScoreDataBase
        }
    }

}