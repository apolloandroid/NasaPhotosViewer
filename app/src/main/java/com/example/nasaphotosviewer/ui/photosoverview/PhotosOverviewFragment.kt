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


class PhotosOverviewFragment : Fragment() {

    private lateinit var viewModel: PhotosOverviewViewModel
    private lateinit var binding: FragmentPhotosOverviewBinding
    private lateinit var photosListAdapter: PhotosListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val date = PhotosOverviewFragmentArgs.fromBundle(arguments).date

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_photos_overview, container, false)
        viewModel = initViewModel()
        initPhotosList(viewModel)
        viewModel.getPhotosForDate(date)

        viewModel.photos.observe(this, Observer {
            photosListAdapter.submitList(it)
        })

        viewModel.photoClicked.observe(this, Observer {
            navigateToPhotoDetailsFragment(it)
        })

        return binding.root
    }

    private fun initViewModel(): PhotosOverviewViewModel {
        val application = App
        val photosOverviewViewModelFactory = PhotosOverviewViewModelFactory(application)
        return photosOverviewViewModelFactory.create(PhotosOverviewViewModel::class.java)
    }

    private fun initPhotosList(viewModel: PhotosOverviewViewModel) {
        photosListAdapter = PhotosListAdapter(viewModel)
        binding.photoList.adapter = photosListAdapter
        binding.photoList.setHasFixedSize(true)
    }

    private fun navigateToPhotoDetailsFragment(photoUrl: String) {
        if (findNavController().currentDestination?.id == R.id.photosOverviewFragment) {
            findNavController().navigate(
                PhotosOverviewFragmentDirections.actionPhotosOverviewFragmentToPhotoDetailsFragment(
                    photoUrl
                )
            )
        }
    }
}