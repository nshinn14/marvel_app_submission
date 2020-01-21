package com.nshinn.marvellimited.persistence.dao

import androidx.room.*
import com.nshinn.marvellimited.persistence.model.Comic

@Dao
abstract class ComicDao : BaseDao<Comic> {
    @Query("SELECT * FROM Comic")
    abstract fun getAll(): List<Comic>

    @Insert
    abstract fun insertAll(vararg Comics: Comic)

}