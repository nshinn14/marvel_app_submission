package com.nshinn.marvellimited.persistence.model

import androidx.room.*
import java.util.*

@Entity
data class Comic(
    @PrimaryKey(autoGenerate = true) val id: Int,

    val title : String?,
    val description : String?,
    val modified : String?,
    val thumbnail : String?

    // Foreign Keys
//    val seriesId : Int,
//    val storyId : Int
)
