package com.nshinn.marvellimited.persistence.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * Base DAO interface with generic functions.
 * Note that insert has REPLACE strategy upon conflict.
 */
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(objList: List<T>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T): Long

    @Update
    fun update(obj: T)

    @Delete
    fun delete(obj: T)

}