package com.nshinn.marvellimited.net.entity

import com.squareup.moshi.Json

data class MarvelComicData(
    @field:Json(name = "results")
    val results: List<MarvelComic>
)