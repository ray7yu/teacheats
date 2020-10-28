package com.teach.eats.fragments.result

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.teach.eats.Photo
import com.teach.eats.R
import com.teach.eats.databinding.FragmentResultBinding
import java.io.File


class ResultFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentResultBinding>(
            inflater,
            R.layout.fragment_result,
            container,
            false
        )
        //Custom back pressed callback that also deletes photo
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d(TAG, "Fragment back pressed invoked")
                    Photo.deleteImage(arguments)
                    if (isEnabled) {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            }
        )

        //Sets image after layout methods have been called, views are in place, and activity is ready to be displayed
        val myView: View = binding.foodPicture
        myView.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            @SuppressLint("NewApi")
            override fun onGlobalLayout() {
                Photo.getImage(binding, arguments)
                // Once data has been obtained, this listener is no longer needed, so remove it...
                myView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

        //Label is set using Photo class
        Photo.getLabel(binding, arguments)

        //Pressing return button will delete image and return to title fragment
        binding.resultReturnButton.setOnClickListener { view: View ->
            Photo.deleteImage(arguments)
            view.findNavController().navigate(R.id.action_resultFragment_to_titleFragment)
        }
        //Pressing learn will go to food name fragment, while passing bundle
        binding.learnButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_resultFragment_to_foodNameFragment, arguments)
        }
        binding.saveButton.setOnClickListener {
            savePicture()
        }
        //If no label, learn button is not visible
        if (arguments?.getString("label").toString() == "") {
            binding.learnButton.visibility = View.INVISIBLE
            binding.saveButton.visibility = View.INVISIBLE
        }
        return binding.root
    }
    private fun savePicture(){
        val photoPath = arguments?.getString("photoPath").toString()
        val f = File(photoPath)
        MediaScannerConnection.scanFile(context, arrayOf(photoPath), null,null)
    }
}