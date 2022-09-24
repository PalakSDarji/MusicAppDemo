package com.palak.applemusicappdemo.repo

import com.palak.applemusicappdemo.api.PostingApiInterface
import com.palak.applemusicappdemo.models.PostingResponse
import io.reactivex.rxjava3.core.Observable

import javax.inject.Inject

class PostingRepo @Inject constructor(private val postingApiInterface: PostingApiInterface) {

    fun getPosts() : Observable<PostingResponse> {
        return postingApiInterface.getPosts()
    }
}