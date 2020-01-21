package com.nshinn.marvellimited.net.entity

import com.squareup.moshi.Json

data class MarvelComicResponse(
    @field:Json(name = "code")
    val code: Double,
    @field:Json(name = "status")
    val status: String,
    @field:Json(name = "copyright")
    val copyright: String,
    @field:Json(name = "data")
    val data: MarvelComicData
)