package com.palak.applemusicappdemo.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.palak.applemusicappdemo.R
import com.palak.applemusicappdemo.databinding.ItemSongBinding
import com.palak.applemusicappdemo.models.SongEntry
import com.palak.applemusicappdemo.utils.SongEntryDiffCallback

/**
 * This adapter loads song data in home screen.
 */
class SongAdapter(var onClick: (SongEntry, Int) -> Unit) :
    ListAdapter<Any, RecyclerView.ViewHolder>(SongEntryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SongViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_song, parent, false
            ), onClick
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as SongViewHolder
        holder.bind(getItem(position) as SongEntry)
    }

    class SongViewHolder(private val binding: ItemSongBinding, var onClick: (SongEntry, Int) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(song: SongEntry) {
            with(binding) {
                binding.container.setOnClickListener {
                    onClick(song, bindingAdapterPosition)
                }
                binding.songEntry = song
                executePendingBindings()
            }
        }
    }
}

