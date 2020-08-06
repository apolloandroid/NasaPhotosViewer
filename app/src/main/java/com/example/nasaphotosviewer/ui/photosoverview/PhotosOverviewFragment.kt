package com.example.nasaphotosviewer.ui.photosoverview

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
import com.example.nasaphotosviewer.databinding.FragmentPhotosOverviewBinding
import com.example.nasaphotosviewer.ui.dateoverview.DateListAdapter


class PhotosOverviewFragment : Fragment() {

    private val viewModel: PhotosOverviewViewModel by lazy { initViewModel() }
    private lateinit var binding: FragmentPhotosOverviewBinding
    private var photosListAdapter = PhotosListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_photos_overview, container, false)
        initPhotosList()

        viewModel.photos.observe(this, Observer {
            photosListAdapter.submitList(it)
        })

        viewModel.photoClicked.observe(this, Observer {
            if (it) navigateToPhotoDetailsFragment()
        })

        return binding.root
    }

    private fun initViewModel(): PhotosOverviewViewModel {
        val application = App()
        val photosOverviewViewModelFactory = PhotosOverviewViewModelFactory(application)
        return photosOverviewViewModelFactory.create(PhotosOverviewViewModel::class.java)
    }

    private fun initPhotosList() {
        binding.photoList.adapter = photosListAdapter
        binding.photoList.setHasFixedSize(true)
    }

    private fun navigateToPhotoDetailsFragment() {
        if (findNavController().currentDestination?.id == R.id.photosOverviewFragment) {
            findNavController().navigate(R.id.action_photosOverviewFragment_to_photoDetailsFragment)
        }
    }
}