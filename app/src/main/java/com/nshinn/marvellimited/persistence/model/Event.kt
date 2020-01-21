package com.nshinn.marvellimited.persistence.model

import androidx.room.*

@Entity
data class Event(
    @PrimaryKey(autoGenerate = true) val id: Int,

    val uid: String?,
    val title: String?
)
