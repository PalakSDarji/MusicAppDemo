package com.palak.applemusicappdemo.utils

class Constants {

    companion object{

        const val BASE_URL = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/"
        const val GET_TOP_SONGS = "${BASE_URL}topsongs/limit%3D25/xml"

        const val POSTING_BASE_URL = "https://jsonplaceholder.typicode.com/"
        const val GET_POSTS = "${POSTING_BASE_URL}posts"
        const val GET_POST_BY_ID = "${POSTING_BASE_URL}posts/{id}"
        const val GET_COMMENTS_BY_POST_ID = "${POSTING_BASE_URL}posts/{id}/comments"

        const val TOP_SONG_QTY = 20

        const val BUNDLE_SONG_ENTRY = "BUNDLE_SONG_ENTRY"
        const val PREVIEW_TEXT: String = "Preview"
    }
}