package com.palak.applemusicappdemo.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
class PostingResponse : ArrayList<PostingResponseItem>(), Parcelable, Serializable

class PostProvider(var value : PostingResponse?)