package com.nshinn.marvellimited.persistence.dao

import androidx.room.*
import com.nshinn.marvellimited.persistence.model.Comic
import com.nshinn.marvellimited.persistence.model.Comic_Creator

@Dao
abstract class Comic_CreatorDao : BaseDao<Comic_Creator> {

    @Query("SELECT * FROM Comic " +
            "INNER JOIN Comic_Creator ON Comic.id=Comic_Creator.comicId " +
            "WHERE Comic_Creator.creatorId=:creatorId")
    abstract fun getComics(creatorId: Int): List<Comic>

}