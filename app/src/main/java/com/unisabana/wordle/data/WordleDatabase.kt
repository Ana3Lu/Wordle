package com.unisabana.wordle.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ScoreEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class WordleDatabase : RoomDatabase() {

    abstract fun ScoreDao(): ScoreDao

    companion object {
        @Volatile private var INSTANCE: WordleDatabase? = null

        fun getDatabase(context: Context): WordleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordleDatabase::class.java,
                    "database_wordle"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}