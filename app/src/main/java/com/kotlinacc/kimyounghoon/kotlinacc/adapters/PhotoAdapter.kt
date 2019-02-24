package com.kotlinacc.kimyounghoon.kotlinacc.adapters

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kotlinacc.kimyounghoon.kotlinacc.databinding.ItemPhotoBinding
import com.kotlinacc.kimyounghoon.kotlinacc.models.Photo


class PhotoAdapter : PagedListAdapter<Photo, RecyclerView.ViewHolder>(photoDiffCallback) {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            PhotoViewHolder(ItemPhotoBinding.inflate(LayoutInflater.from(viewGroup.context)))

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.apply {
            (viewHolder as PhotoViewHolder).bind(this)
        }
    }

    class PhotoViewHolder(private val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) {
            binding.photo = photo
        }
    }

    companion object {
        val photoDiffCallback = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                    oldItem == newItem
        }
    }
}