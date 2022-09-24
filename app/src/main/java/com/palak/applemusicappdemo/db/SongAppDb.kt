package com.palak.applemusicappdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.palak.applemusicappdemo.models.SongEntry
import com.palak.applemusicappdemo.utils.SongConverter

/**
 * This class provides room db implementation. Once the changes are done, make sure to
 * update the version.
 */
@Database(entities = [SongEntry::class],
            version = 1,
            exportSchema = false)
@TypeConverters(SongConverter::class)
abstract class SongAppDb : RoomDatabase(){

    abstract fun songEntryDao() : SongEntryDao

    companion object {

        @Volatile
        private var INSTANCE: SongAppDb? = null

        fun getDb(context: Context): SongAppDb {

            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SongAppDb::class.java,
                    "my_song_app_db"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }

}