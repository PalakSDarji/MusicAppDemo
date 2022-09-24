package com.palak.applemusicappdemo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.palak.applemusicappdemo.models.SongEntry

/**
 * Handler method for db operations.
 */
@Dao
interface SongEntryDao {

    @Query("SELECT * from entry")
    fun fetchAllSongEntry() : LiveData<List<SongEntry>>

    @Query("SELECT count(*) from entry")
    suspend fun hasAnySongEntry() : Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongEntry(songEntry: SongEntry) : Long

}