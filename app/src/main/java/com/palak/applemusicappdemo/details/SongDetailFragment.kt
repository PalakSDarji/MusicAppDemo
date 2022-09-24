package com.palak.applemusicappdemo.details

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.media3.common.util.Util
import androidx.media3.exoplayer.ExoPlayer
import com.bumptech.glide.Glide
import com.palak.applemusicappdemo.MainActivity
import com.palak.applemusicappdemo.R
import com.palak.applemusicappdemo.databinding.FragmentSongDetailBinding
import com.palak.applemusicappdemo.models.SongEntry
import com.palak.applemusicappdemo.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SongDetailFragment : Fragment() {

    private lateinit var binding: FragmentSongDetailBinding
    private var player: ExoPlayer? = null

    private var songEntry: SongEntry? = null

    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_song_detail, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        songEntry = arguments?.getParcelable(Constants.BUNDLE_SONG_ENTRY)
        binding.songEntry = songEntry
    }

    private fun getUrl(): String? {
        return songEntry?.songLinkList?.firstOrNull {
            it.title == Constants.PREVIEW_TEXT
        }?.previewLink
    }

    private fun initPlayer() {
        getUrl()?.let { url ->
            player = ExoPlayer.Builder(requireContext())
                .build()
                .also { exoPlayer ->
                    binding.videoView.setShowPreviousButton(false)
                    binding.videoView.setShowNextButton(false)
                    binding.videoView.player = exoPlayer

                    val mediaItem = MediaItem.fromUri(url)
                    exoPlayer.setMediaItem(mediaItem)
                    exoPlayer.playWhenReady = playWhenReady
                    exoPlayer.seekTo(currentItem, playbackPosition)
                    exoPlayer.prepare()
                }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            songEntry?.imageList?.lastOrNull()?.let {
                loadImage(it)
            }
        }
    }

    private suspend fun loadImage(url: String) {
        val bitmap = Glide.with(requireContext())
            .asBitmap()
            .load(url)
            .submit()
            .get()

        withContext(Dispatchers.Main) {
            binding.videoView.defaultArtwork = BitmapDrawable(
                resources,
                bitmap
            )
        }
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initPlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if ((Util.SDK_INT <= 23 || player == null)) {
            initPlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        player?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentItem = exoPlayer.currentMediaItemIndex
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.release()
        }
        player = null
    }
}