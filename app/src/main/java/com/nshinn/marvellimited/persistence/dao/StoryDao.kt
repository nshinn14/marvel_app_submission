package com.nshinn.marvellimited.persistence.dao

import androidx.room.*
import com.nshinn.marvellimited.persistence.model.Story

@Dao
abstract class StoryDao : BaseDao<Story> {
    @Query("SELECT * FROM Story")
    abstract fun getAll(): List<Story>
}