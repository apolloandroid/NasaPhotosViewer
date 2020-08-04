package com.example.nasaphotosviewer.ui.photosoverview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaphotosviewer.data.model.Photo
import com.example.nasaphotosviewer.databinding.PhotosListItemBinding

class PhotosListAdapter :
    ListAdapter<Photo, PhotosListAdapter.PhotoViewHolder>(PhotoDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        PhotoViewHolder.from(parent)


    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photo)
    }

    class PhotoViewHolder private constructor(private val binding: PhotosListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) {

        }

        companion object {
            fun from(parent: ViewGroup): PhotoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PhotosListItemBinding.inflate(layoutInflater, parent, false)
                return PhotoViewHolder(binding)
            }
        }
    }

    private class PhotoDiffCallBack : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
            oldItem == newItem
    }
}