package com.palak.applemusicappdemo.post.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.palak.applemusicappdemo.R
import com.palak.applemusicappdemo.models.PostingResponse
import com.palak.applemusicappdemo.models.PostingResponseItem
import com.palak.applemusicappdemo.post.home.destinations.ShowPostDetailDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ShowPostList(navigator : DestinationsNavigator, modifier: Modifier = Modifier, postingResponse: PostingResponse) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = LocalContext.current.getString(R.string.post_home_title))
        LazyColumn {
            itemsIndexed(postingResponse) { _: Int, item: PostingResponseItem ->
                PostItemUI(navigator,item)
            }
        }
    }
}

@Composable
fun PostItemUI(navigator : DestinationsNavigator,item: PostingResponseItem) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable {
            navigator.navigate(ShowPostDetailDestination(item))
        }) {
        Text(text = item.title)
    }
}

@Destination
@Composable
fun ShowEmptyScreen(navigator : DestinationsNavigator, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "No data is added.")
    }
}

@Destination
@Composable
fun ShowPostDetail(navigator : DestinationsNavigator, post: PostingResponseItem){
    Text(text = "THis is detail : ${post.body} ")
}
