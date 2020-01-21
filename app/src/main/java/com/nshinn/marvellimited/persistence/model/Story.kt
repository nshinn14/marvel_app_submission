package com.nshinn.marvellimited.persistence.model

import androidx.room.*

@Entity
data class Story(
    @PrimaryKey(autoGenerate = true) val id: Int,

    val uid: String?,
    val title: String?
)
