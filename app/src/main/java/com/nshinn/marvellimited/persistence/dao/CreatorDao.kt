package com.nshinn.marvellimited.persistence.dao

import androidx.room.*
import com.nshinn.marvellimited.persistence.model.Creator

@Dao
abstract class CreatorDao : BaseDao<Creator> {
    @Query("SELECT * FROM Creator")
    abstract fun getAll(): List<Creator>
}