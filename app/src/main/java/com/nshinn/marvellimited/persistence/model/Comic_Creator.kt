package com.nshinn.marvellimited.persistence.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

// Join Table for Comic : Creator relation
@Entity(
    primaryKeys=["comicId", "creatorId"],
    indices = [
        Index(value = ["comicId"]),
        Index(value = ["creatorId"])
    ],
    foreignKeys = [
        ForeignKey(entity = Comic::class, parentColumns = ["id"], childColumns = ["comicId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Creator::class, parentColumns = ["id"], childColumns = ["creatorId"], onDelete = ForeignKey.CASCADE)
    ])
data class Comic_Creator(
    val comicId: Int,
    val creatorId: Int
)