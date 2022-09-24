package com.palak.applemusicappdemo.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.palak.applemusicappdemo.models.SongEntry

/**
 * This is safety mechanism to only update the recyclerview if two items are really not equal.
 * Here we define equality of songEntry based on it's title.
 */
class SongEntryDiffCallback : DiffUtil.ItemCallback<Any>() {

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        if (oldItem is SongEntry && newItem is SongEntry) {
            return oldItem.title == newItem.title
        }
        return false
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        if (oldItem is SongEntry && newItem is SongEntry) {
            return oldItem == newItem
        }
        return false
    }
}