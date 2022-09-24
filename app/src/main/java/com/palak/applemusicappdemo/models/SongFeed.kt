package com.palak.applemusicappdemo.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.palak.applemusicappdemo.utils.SongConverter
import kotlinx.parcelize.Parcelize
import org.simpleframework.xml.*

/**
 * A model to represent room table, xml parsing. It only parses given fields.
 */
@Parcelize
@Root(name = "feed", strict = false)
data class SongFeed @JvmOverloads constructor(

    @field:ElementList(name = "entry", inline = true)
    var entryList : ArrayList<SongEntry>? = null

) : Parcelable

@Parcelize
@Entity(tableName = "entry")
@Root(name = "entry", strict = false)
data class SongEntry @JvmOverloads constructor(

    @PrimaryKey
    @field:Element(name = "title")
    var title : String = "",

    @TypeConverters(SongConverter::class)
    @ColumnInfo(name = "link")
    @field:ElementList(name = "link", inline = true)
    var songLinkList : ArrayList<SongLink>? = null,

    @ColumnInfo(name = "name")
    @TypeConverters(SongConverter::class)
    @field:Namespace(reference = "http://itunes.apple.com/rss", prefix = "im")
    @field:ElementList(entry = "image", inline = true)
    var imageList : ArrayList<String>? = null

) : Parcelable

@Parcelize
@Root(name = "link", strict = false)
data class SongLink @JvmOverloads constructor(

    @field:Attribute(name = "title", required = false)
    var title : String? = null,

    @field:Attribute(name = "rel")
    var rel : String? = null,

    @field:Attribute(name = "type")
    var type : String? = null,

    @field:Attribute(name = "href")
    var previewLink : String? = null

) : Parcelable