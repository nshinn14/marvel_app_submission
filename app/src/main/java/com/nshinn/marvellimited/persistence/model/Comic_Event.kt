package com.nshinn.marvellimited.persistence.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

// Join Table for Comic : Event relation
@Entity(
    primaryKeys=["comicId", "eventId"],
    indices = [
        Index(value = ["comicId"]),
        Index(value = ["eventId"])
    ],
    foreignKeys = [
        ForeignKey(entity = Comic::class, parentColumns = ["id"], childColumns = ["comicId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Event::class, parentColumns = ["id"], childColumns = ["eventId"], onDelete = ForeignKey.CASCADE)
    ])
data class Comic_Event(
    val comicId: Int,
    val eventId: Int
)