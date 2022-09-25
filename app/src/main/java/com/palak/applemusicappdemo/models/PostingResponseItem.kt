package com.palak.applemusicappdemo.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class PostingResponseItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
) : Parcelable, Serializable