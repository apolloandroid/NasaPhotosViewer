package com.example.nasaphotosviewer.ui.dateoverview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaphotosviewer.data.model.Date
import com.example.nasaphotosviewer.databinding.DatesListItemBinding

class DateListAdapter(private val itemClickListener: OnDateClickListener<Date>) :
    ListAdapter<Date, DateListAdapter.DateViewHolder>(DateDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val viewHolder = DateViewHolder.from(parent)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION) itemClickListener.onDateClick()
        }
        return viewHolder
    }


    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        val date = getItem(position)
        holder.bind(date)
    }

    class DateViewHolder private constructor(private val binding: DatesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(date: Date) {
            binding.date = date
        }

        companion object {
            fun from(parent: ViewGroup): DateViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DatesListItemBinding.inflate(layoutInflater, parent, false)
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

    interface OnDateClickListener<Date> {
        fun onDateClick()
    }
}