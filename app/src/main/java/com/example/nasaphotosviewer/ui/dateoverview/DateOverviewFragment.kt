package com.example.nasaphotosviewer.ui.dateoverview

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaphotosviewer.App
import com.example.nasaphotosviewer.R
import com.example.nasaphotosviewer.databinding.FragmentDateOverviewBinding
import java.sql.Date


class DateOverviewFragment : Fragment() {
    private val viewModel: DateOverviewViewModel by lazy { initViewModel() }
    private lateinit var binding: FragmentDateOverviewBinding
    private var dateListAdapter = DateListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_date_overview, container, false)
        initDatesList()
        viewModel.datesList.observe(this, Observer { datesList ->
            dateListAdapter.submitList(datesList)
        })

        return binding.root
    }

    private fun initViewModel(): DateOverviewViewModel {
        val application = Application()
        val overviewViewModelFactory = DateOverviewViewModelFactory(application)
        return overviewViewModelFactory.create(DateOverviewViewModel::class.java)
    }

    private fun initDatesList() {
        binding.dateList.adapter = dateListAdapter
        binding.dateList.setHasFixedSize(true)
    }
}