package com.nshinn.marvellimited.persistence.dao

import androidx.room.*
import com.nshinn.marvellimited.persistence.model.Comic
import com.nshinn.marvellimited.persistence.model.Comic_Creator

@Dao
abstract class Comic_EventDao : BaseDao<Comic_Creator> {

    @Query("SELECT * FROM Comic " +
            "INNER JOIN Comic_Event ON Comic.id=Comic_Event.comicId " +
            "WHERE Comic_Event.eventId=:eventId")
    abstract fun getComics(eventId: Int): List<Comic>

}