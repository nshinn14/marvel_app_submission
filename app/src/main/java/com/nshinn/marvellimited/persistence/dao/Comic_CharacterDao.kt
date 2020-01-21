package com.nshinn.marvellimited.persistence.dao

import androidx.room.*
import com.nshinn.marvellimited.persistence.model.Comic
import com.nshinn.marvellimited.persistence.model.Comic_Character

@Dao
abstract class Comic_CharacterDao : BaseDao<Comic_Character> {

    @Query("SELECT * FROM Comic " +
            "INNER JOIN Comic_Character ON Comic.id=Comic_Character.comicId " +
            "WHERE Comic_Character.characterId=:characterId")
    abstract fun getComics(characterId: Int): List<Comic>

}