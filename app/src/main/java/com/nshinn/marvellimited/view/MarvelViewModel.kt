package com.nshinn.marvellimited.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nshinn.marvellimited.net.MarvelApiFactory
import com.nshinn.marvellimited.net.entity.MarvelComic
import com.nshinn.marvellimited.net.netsec.CryptoUtil
import com.nshinn.marvellimited.net.repository.ComicRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MarvelViewModel : ViewModel(){

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository : ComicRepository = ComicRepository(MarvelApiFactory.marvelApi)

    val marvelComics = MutableLiveData<MutableList<MarvelComic>>()

    fun fetchComics(){
        scope.launch {
            CryptoUtil.setRequestTimeStamp()
            val comics = repository.getComics()
            marvelComics.postValue(comics)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()

}