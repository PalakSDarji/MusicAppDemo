package com.palak.applemusicappdemo.api

import com.palak.applemusicappdemo.models.CommentsResponse
import com.palak.applemusicappdemo.models.PostingResponse
import com.palak.applemusicappdemo.models.PostingResponseItem
import com.palak.applemusicappdemo.utils.Constants
import io.reactivex.rxjava3.core.Observable

import retrofit2.http.GET
import retrofit2.http.Path

interface PostingApiInterface {

    @GET(Constants.GET_POSTS)
    fun getPosts() : Observable<PostingResponse>

    @GET(Constants.GET_POST_BY_ID)
    fun getPostById(@Path("id") id: Int) : Observable<PostingResponseItem>

    @GET(Constants.GET_COMMENTS_BY_POST_ID)
    fun getCommentsByPostId(@Path("id") id: Int) : Observable<CommentsResponse>
}