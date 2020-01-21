package com.nshinn.marvellimited.net.repository

import com.nshinn.marvellimited.net.*
import com.nshinn.marvellimited.net.entity.MarvelComic

class ComicRepository(private val api : MarvelApi) : BaseRepository() {

    suspend fun getComics() : MutableList<MarvelComic>? {
        val movieResponse = safeApiCall(
            call = {api.getComics().await()},
            errorMessage = "Error Fetching Popular Comics"
        )

        return movieResponse?.data?.results?.toMutableList()

    }

}