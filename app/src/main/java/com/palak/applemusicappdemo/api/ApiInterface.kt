package com.palak.applemusicappdemo.api

import com.palak.applemusicappdemo.models.SongFeed
import com.palak.applemusicappdemo.utils.Constants
import retrofit2.http.*

/**
 * Interface to contain methods retrofit will work with.
 */
@JvmSuppressWildcards
interface ApiInterface {

    @GET(Constants.GET_TOP_SONGS)
    suspend fun getTopSongs(): SongFeed?
}

