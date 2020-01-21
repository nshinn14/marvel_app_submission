package com.nshinn.marvellimited.persistence.repository

import android.app.Application
import com.nshinn.marvellimited.persistence.dao.ComicDao
import com.nshinn.marvellimited.persistence.database.UniverseDatabase
import com.nshinn.marvellimited.persistence.model.Comic

class ComicRepository(private val comicDao: ComicDao): BaseRepository<Comic>(comicDao) {

    fun getComics(): List<Comic> {
        return comicDao.getAll()
    }

    companion object {
        fun createRepository(application : Application) =
            ComicRepository(UniverseDatabase.getInstance(application).comicDao())
    }
}