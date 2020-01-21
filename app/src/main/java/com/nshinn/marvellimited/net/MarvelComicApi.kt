package com.nshinn.marvellimited.net

import com.nshinn.marvellimited.net.entity.MarvelComicResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface MarvelApi {

    @GET("public/comics?limit=15")
    fun getComics(): Deferred<Response<MarvelComicResponse>>

}