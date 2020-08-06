package com.example.nasaphotosviewer.ui.photosoverview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaphotosviewer.data.model.Date
import com.example.nasaphotosviewer.data.model.Photo
import com.example.nasaphotosviewer.databinding.PhotosListItemBinding
import com.example.nasaphotosviewer.ui.dateoverview.DateListAdapter

class PhotosListAdapter(private val itemClickListener: OnPhotoClickListener<Photo>) :
    ListAdapter<Photo, PhotosListAdapter.PhotoViewHolder>(PhotoDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val viewHolder = PhotoViewHolder.from(parent)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION) itemClickListener.onPhotoClick()
        }
        return viewHolder
    }


    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photo)
    }

    class PhotoViewHolder private constructor(private val binding: PhotosListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) {
            binding.photo = photo
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

    interface OnPhotoClickListener<Photo> {
        fun onPhotoClick()
    }
}