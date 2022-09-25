package com.palak.applemusicappdemo.post.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.palak.applemusicappdemo.R
import com.palak.applemusicappdemo.models.PostProvider
import com.palak.applemusicappdemo.models.PostingResponse
import com.palak.applemusicappdemo.post.home.destinations.ShowEmptyScreenDestination
import com.palak.applemusicappdemo.post.home.destinations.ShowPostListDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.NavHostParam
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostHomeFragment : Fragment() {

    private var postingResponse : PostingResponse? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        postingResponse = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            arguments?.getParcelable("posts",PostingResponse::class.java)
        } else {
            arguments?.getParcelable("posts")
        }


        val view = inflater.inflate(R.layout.fragment_post_home,container,false)

        view.findViewById<ComposeView>(R.id.postHomeComposeView).setContent {
            MaterialTheme {
                DestinationsNavHost(navGraph = NavGraphs.root,
                dependenciesContainerBuilder = {
                    dependency(PostProvider(postingResponse))
                })
            }
        }

        return view.rootView
    }
}

@RootNavGraph(start = true)
@Destination
@Composable
fun PostHome(navigator: DestinationsNavigator, @NavHostParam postProvider: PostProvider){
    LaunchedEffect(key1 = postProvider.value){
        if(postProvider.value != null && !postProvider.value!!.isEmpty()){
            navigator.navigate(ShowPostListDestination(postingResponse = postProvider.value!!))
        }
        else {
            navigator.navigate(ShowEmptyScreenDestination)
        }
    }
}
