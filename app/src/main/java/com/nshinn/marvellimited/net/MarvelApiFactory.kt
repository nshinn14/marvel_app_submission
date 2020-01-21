package com.nshinn.marvellimited.net

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nshinn.marvellimited.AppConstants
import com.nshinn.marvellimited.net.netsec.CryptoUtil
import com.nshinn.marvellimited.net.netsec.md5
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object MarvelApiFactory {
    private val BASE_URL = "https://gateway.marvel.com/v1/"

    /**
      * Creating okhttp Auth Interceptor to add required parameters
      * in front of all the requests. Used in Retrofit
      *
      * From the marvel api docs:
      * Server-side applications must pass two parameters in addition to the apikey parameter:
      * ts - a timestamp (or other long string which can change on a request-by-request basis)
      * hash - a md5 digest of the ts parameter, your private key and your public key (e.g. md5(ts+privateKey+publicKey)
      *
      * For example, a user with a public key of "1234" and a private key of "abcd"
      * could construct a valid call as follows:
      * http://gateway.marvel.com/v1/public/comics?ts=1&apikey=1234&hash=ffd275c5130566a2916217b101f26150 (the hash value is the md5 digest of 1abcd1234)
      */
    private val authInterceptor = Interceptor {chain->
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("ts", CryptoUtil.currentTimeStamp)
            .addQueryParameter("apikey", AppConstants.marvelApiKey)
            .addQueryParameter("hash", (CryptoUtil.currentTimeStamp + AppConstants.marvelApiSecret + AppConstants.marvelApiKey).md5())
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    // OkhttpClient for building http request url
    private val marvelClient = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .build()

    private fun retrofit() : Retrofit = Retrofit.Builder()
        .client(marvelClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val marvelApi : MarvelApi = retrofit().create(MarvelApi::class.java)

}