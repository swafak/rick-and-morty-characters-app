package com.example.rick_and_morty_characters_app.model.dataSource.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
abstract class CharacterDB: RoomDatabase() {

    abstract fun CharacterDao(): CharacterDao

    companion object{
        @Volatile private var INSTANCE: CharacterDB ? = null

        fun getDatabase(context: Context): CharacterDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterDB::class.java,
                    "Rick-And-Morty"
                ).build()
                INSTANCE = instance
                instance
            }

        }
    }
}