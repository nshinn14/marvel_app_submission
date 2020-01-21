package com.nshinn.marvellimited.persistence.dao

import androidx.room.*
import com.nshinn.marvellimited.persistence.model.Series

@Dao
abstract class SeriesDao : BaseDao<Series> {
    @Query("SELECT * FROM Series")
    abstract fun getAll(): List<Series>
}