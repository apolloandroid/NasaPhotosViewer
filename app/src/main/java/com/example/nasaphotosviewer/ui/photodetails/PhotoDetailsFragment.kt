package com.example.nasaphotosviewer.ui.photodetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.nasaphotosviewer.App
import com.example.nasaphotosviewer.R
import com.example.nasaphotosviewer.databinding.FragmentPhotoDetailsBinding


class PhotoDetailsFragment : Fragment() {

    private val viewModel: PhotoDetailsViewModel by lazy { initViewModel() }
    private lateinit var binding: FragmentPhotoDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_photo_details, container, false)
        initViewModel()
        return binding.root
    }

    private fun initViewModel(): PhotoDetailsViewModel {
        val application = App()
        val photoDetailViewModelFactory = PhotoDetailsViewModelFactory(application)
        return photoDetailViewModelFactory.create(PhotoDetailsViewModel::class.java)
    }
}