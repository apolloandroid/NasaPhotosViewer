package com.example.nasaphotosviewer.ui.dateoverview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.nasaphotosviewer.App
import com.example.nasaphotosviewer.R
import com.example.nasaphotosviewer.databinding.FragmentDateOverviewBinding


class DateOverviewFragment : Fragment() {
    private val viewModel: DateOverviewViewModel by lazy { initViewModel() }
    private lateinit var binding: FragmentDateOverviewBinding
    private var dateListAdapter = DateListAdapter(viewModel)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_date_overview, container, false)
        initDatesList()

        viewModel.dates.observe(this, Observer {
            dateListAdapter.submitList(it)
        })

        viewModel.dateClicked.observe(this, Observer {
            navigateToPhotosOverviewFragment(it)
        })

        return binding.root
    }

    private fun initViewModel(): DateOverviewViewModel {
        val application = App()
        val viewViewModelFactory = DateOverviewViewModelFactory(application)
        return viewViewModelFactory.create(DateOverviewViewModel::class.java)
    }

    private fun initDatesList() {
        binding.dateList.adapter = dateListAdapter
        binding.dateList.setHasFixedSize(true)
    }

    private fun navigateToPhotosOverviewFragment(date: String) {
        if (findNavController().currentDestination?.id == R.id.dateOverviewFragment) {
            findNavController().navigate(
                DateOverviewFragmentDirections.actionDateOverviewFragmentToPhotosOverviewFragment(
                    date
                )
            )
        }
    }
}