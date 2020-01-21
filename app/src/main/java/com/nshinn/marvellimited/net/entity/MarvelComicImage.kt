package com.nshinn.marvellimited.net.entity

import com.squareup.moshi.Json

data class MarvelComicImage(
    @field:Json(name = "path")
    val path: String,
    @field:Json(name = "extension")
    val extension: String
)
