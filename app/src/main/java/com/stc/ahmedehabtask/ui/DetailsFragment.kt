package com.stc.ahmedehabtask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.stc.ahmedehabtask.R
import com.stc.ahmedehabtask.databinding.DetailsFragmentBinding
import com.stc.ahmedehabtask.utilities.Constants.STORAGE_URL
import com.stc.ahmedehabtask.utilities.ImageHelper.loadImage

class DetailsFragment : Fragment() {
    private lateinit var binding: DetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        val originalTitle = arguments?.getString("original_title")
        val posterPath = arguments?.getString("poster_path")
        binding.title.text = originalTitle
        binding.back.setOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }
        binding.postImage.clipToOutline = true
        loadImage(
            requireActivity(),
            STORAGE_URL + posterPath,
            R.mipmap.ic_launcher,
            binding.postImage
        )
    }

}