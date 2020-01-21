package com.nshinn.marvellimited.persistence.model

import androidx.room.*

@Entity
data class Creator(
    @PrimaryKey(autoGenerate = true) val id: Int,

    val uid: String?,
    val fullName: String?
)
