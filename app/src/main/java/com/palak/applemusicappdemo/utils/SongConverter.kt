package com.palak.applemusicappdemo.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.palak.applemusicappdemo.models.SongLink
import java.lang.reflect.Type

/**
 * Converter allows complex objects to be stored in room db as it does not allow complex references.
 *
 * We convert the complex object into String and vice versa. This Converter goes into room db configs.
 */
class SongConverter {

    private var gson: Gson = Gson()

    @TypeConverter
    fun stringToSongLinkList(data: String?): ArrayList<SongLink>? {
        if (data == null) {
            return arrayListOf()
        }

        val listType: Type = object : TypeToken<ArrayList<SongLink>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun songLinkListToString(someObjects: ArrayList<SongLink>?): String? {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringToSongImageList(data: String?): ArrayList<String>? {
        if (data == null) {
            return arrayListOf()
        }

        val listType: Type = object : TypeToken<ArrayList<String>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun songImageListToString(someObjects: ArrayList<String>?): String? {
        return gson.toJson(someObjects)
    }
}