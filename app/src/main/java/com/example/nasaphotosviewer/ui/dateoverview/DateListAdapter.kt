package com.example.nasaphotosviewer.ui.dateoverview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaphotosviewer.data.model.DateResponse
import com.example.nasaphotosviewer.databinding.DateListItemBinding

class DateListAdapter :
    ListAdapter<DateResponse, DateListAdapter.DateViewHolder>(DateDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder =
        DateViewHolder.from(parent)

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        val date = getItem(position)
        holder.bind(date)
    }

    class DateViewHolder private constructor(private val binding: DateListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(date: DateResponse) {
            binding.date = date
        }

        companion object {
            fun from(parent: ViewGroup): DateViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DateListItemBinding.inflate(layoutInflater, parent, false)
                return DateViewHolder(binding)
            }
        }
    }

    private class DateDiffCallBack : DiffUtil.ItemCallback<DateResponse>() {
        override fun areItemsTheSame(oldItem: DateResponse, newItem: DateResponse): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: DateResponse, newItem: DateResponse): Boolean =
            oldItem == newItem
    }
}