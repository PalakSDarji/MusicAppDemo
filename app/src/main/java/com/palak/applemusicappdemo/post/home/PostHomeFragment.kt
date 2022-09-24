package com.palak.applemusicappdemo.post.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.palak.applemusicappdemo.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_post_home,container,false)

        view.findViewById<ComposeView>(R.id.postHomeComposeView).setContent {
            MaterialTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(text = requireContext().getString(R.string.post_home_title))
                    LazyColumn{
                        items(3){
                            PostItemUI()
                        }
                    }
                }
            }
        }

        return view.rootView
    }
}