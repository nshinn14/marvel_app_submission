package com.nshinn.marvellimited.persistence.dao

import androidx.room.*
import com.nshinn.marvellimited.persistence.model.Character

@Dao
abstract class CharacterDao : BaseDao<Character> {
    @Query("SELECT * FROM Character")
    abstract fun getAll(): List<Character>
}