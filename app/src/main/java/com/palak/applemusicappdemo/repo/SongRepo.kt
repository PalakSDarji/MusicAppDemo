package com.palak.applemusicappdemo.repo

import androidx.lifecycle.LiveData
import com.palak.applemusicappdemo.api.ApiInterface
import com.palak.applemusicappdemo.db.SongEntryDao
import com.palak.applemusicappdemo.models.SongEntry
import com.palak.applemusicappdemo.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * This repo is the handle between UI and db.
 * UI that calls for the data does not need to know anything about where the data is coming from.
 *
 */
class SongRepo @Inject constructor(
    private val songEntryDao: SongEntryDao,
    private val apiInterface: ApiInterface) {

    fun fetchAllSongEntry(): LiveData<List<SongEntry>> {
        return songEntryDao.fetchAllSongEntry()
    }

    suspend fun checkAndFetch() {
        val count = songEntryDao.hasAnySongEntry()

        if(count == 0){
            //Db is empty. Let's call server.
            fetchTopSongsFromServer()?.forEach { songEntry ->
                //add each songEntry to room db
                songEntryDao.insertSongEntry(songEntry)
            }
        }
    }

    /**
     * Fetch only first 20 as per the business rule.
     */
    private suspend fun fetchTopSongsFromServer() : List<SongEntry>? {
        return withContext(Dispatchers.IO) {
            apiInterface.getTopSongs()?.entryList?.take(Constants.TOP_SONG_QTY)?.map {
                it
            }
        }
    }
}