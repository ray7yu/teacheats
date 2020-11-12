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
    private lateinit var results : Bundle
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
        results = Bundle()
        if(savedInstanceState != null) {
            results.putString("label", savedInstanceState.getString("label", ""))
            results.putString("photoPath", savedInstanceState.getString("photoPath", ""))
        } else {
            results.putString("label", requireArguments().getString("label", ""))
            results.putString("photoPath", requireArguments().getString("photoPath", ""))
        }
        //Custom back pressed callback that also deletes photo
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d(TAG, "Fragment back pressed invoked")
                    Photo.deleteImage(results)
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
                Photo.getImage(binding, results)
                // Once data has been obtained, this listener is no longer needed, so remove it...
                myView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

        //Label is set using Photo class
        Photo.getLabel(binding, results)

        //Pressing return button will delete image and return to title fragment
        binding.resultReturnButton.setOnClickListener { view: View ->
            Photo.deleteImage(results)
            view.findNavController().navigate(R.id.action_resultFragment_to_titleFragment)
        }
        //Pressing learn will go to food name fragment, while passing bundle
        binding.learnButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_resultFragment_to_foodNameFragment, results)
        }
        binding.saveButton.setOnClickListener {
            savePicture()
        }
        //If no label, learn button is not visible
        if (results.getString("label") == "") {
            binding.learnButton.visibility = View.INVISIBLE
            binding.saveButton.visibility = View.INVISIBLE
        }
        return binding.root
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("label", results.getString("label"))
        outState.putString("photoPath", results.getString("photoPath"))
    }
    private fun savePicture(){
        val photoPath = results.getString("photoPath").toString()
        val f = File(photoPath)
        MediaScannerConnection.scanFile(context, arrayOf(photoPath), null,null)
    }
}