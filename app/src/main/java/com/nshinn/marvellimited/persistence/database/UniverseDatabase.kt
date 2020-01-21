package com.nshinn.marvellimited.persistence.database

import android.content.Context
import androidx.room.*
import com.nshinn.marvellimited.persistence.dao.*
import com.nshinn.marvellimited.persistence.model.*

@Database(version = 1,
        entities = [
            Comic::class,
            Comic_Character::class,
            Comic_Creator::class,
            Comic_Event::class,
            Character::class,
            Creator::class,
            Event::class,
            Series::class,
            Story::class
        ])
abstract class UniverseDatabase : RoomDatabase() {
    abstract fun comicDao(): ComicDao
    abstract fun comicCharacterDao(): Comic_CharacterDao
    abstract fun comicCreatorDao(): Comic_CreatorDao
    abstract fun comicEventDao(): Comic_EventDao
    abstract fun characterDao(): CharacterDao
    abstract fun creatorDao(): CreatorDao
    abstract fun eventDao(): EventDao
    abstract fun seriesDao(): SeriesDao
    abstract fun storyDao(): StoryDao

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: UniverseDatabase? = null
        private const val DATABASE_NAME = "UniverseDB.sqlite3"

        internal fun getInstance(context : Context): UniverseDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(context.applicationContext,
                    UniverseDatabase::class.java, DATABASE_NAME)
                    .build()
            }
        }


    }
}
