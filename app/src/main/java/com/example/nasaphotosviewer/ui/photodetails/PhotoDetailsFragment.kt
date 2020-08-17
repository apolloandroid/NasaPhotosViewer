package com.example.nasaphotosviewer.ui.photodetails

import android.app.WallpaperManager
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import androidx.core.content.ContentResolverCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.nasaphotosviewer.App
import com.example.nasaphotosviewer.R
import com.example.nasaphotosviewer.databinding.FragmentPhotoDetailsBinding
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class PhotoDetailsFragment : Fragment() {

    private val viewModel: PhotoDetailsViewModel by lazy { initViewModel() }
    private lateinit var binding: FragmentPhotoDetailsBinding
    private lateinit var photoUrl: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        photoUrl = PhotoDetailsFragmentArgs.fromBundle(arguments).photoUrl
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_photo_details, container, false)
        initViewModel()
        setImagePhoto(photoUrl)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    private fun initViewModel(): PhotoDetailsViewModel {
        val application = App
        val photoDetailViewModelFactory = PhotoDetailsViewModelFactory(application)
        return photoDetailViewModelFactory.create(PhotoDetailsViewModel::class.java)
    }

    private fun setImagePhoto(photoUrl: String) {
        Picasso.get().load(photoUrl).into(binding.imagePhoto)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_photo_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_set_wallpaper -> setAsWallpaper()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setAsWallpaper() {
        Picasso.get().load(photoUrl).into(object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                val wallpaperManager =
                    WallpaperManager.getInstance(activity?.applicationContext)
                try {
                    wallpaperManager.setBitmap(bitmap)
                } catch (ex: IOException) {
                    ex.printStackTrace()
                }
            }
        })
    }

}