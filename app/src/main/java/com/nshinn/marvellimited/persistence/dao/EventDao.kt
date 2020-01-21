package com.nshinn.marvellimited.persistence.dao

import androidx.room.*
import com.nshinn.marvellimited.persistence.model.Event

@Dao
abstract class EventDao : BaseDao<Event> {
    @Query("SELECT * FROM Event")
    abstract fun getAll(): List<Event>
}