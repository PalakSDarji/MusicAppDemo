package com.palak.applemusicappdemo.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostUnited(val postingResponse: PostingResponse,val post1 : PostingResponseItem) : Parcelable