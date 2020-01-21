package com.nshinn.marvellimited.net.entity

import com.squareup.moshi.Json

data class MarvelComic(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "issueNumber")
    val issueNumber : Double,
    @field:Json(name = "description")
    val description : String,
    @field:Json(name = "modified")
    val modified : String,
    @field:Json(name = "thumbnail")
    val thumbnail : MarvelComicImage
)
