package com.nshinn.marvellimited.persistence.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

// Join Table for Comic : Character relation
@Entity(
    primaryKeys=["comicId", "characterId"],
    indices = [
        Index(value = ["comicId"]),
        Index(value = ["characterId"])
    ],
    foreignKeys = [
        ForeignKey(entity = Comic::class, parentColumns = ["id"], childColumns = ["comicId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Character::class, parentColumns = ["id"], childColumns = ["characterId"], onDelete = ForeignKey.CASCADE)
    ])
data class Comic_Character(
    val comicId: Int,
    val characterId: Int
)