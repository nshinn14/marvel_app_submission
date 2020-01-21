package com.nshinn.marvellimited.persistence.model

import androidx.room.*

@Entity
data class Character(
    @PrimaryKey(autoGenerate = true) val id: Int,

    val uid: String?,
    val name: String?
)
