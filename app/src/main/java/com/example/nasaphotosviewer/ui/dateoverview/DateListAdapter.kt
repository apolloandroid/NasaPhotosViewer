package com.example.nasaphotosviewer.ui.dateoverview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaphotosviewer.data.model.Date
import com.example.nasaphotosviewer.databinding.DateListItemBinding

class DateListAdapter :
    ListAdapter<Date, DateListAdapter.DateViewHolder>(DateDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder =
        DateViewHolder.from(parent)

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        val date = getItem(position)
        holder.bind(date)
    }

    class DateViewHolder private constructor(private val binding: DateListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(date: Date) {
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

    private class DateDiffCallBack : DiffUtil.ItemCallback<Date>() {
        override fun areItemsTheSame(oldItem: Date, newItem: Date): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Date, newItem: Date): Boolean =
            oldItem == newItem
    }
}