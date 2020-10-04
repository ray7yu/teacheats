package com.example.teacheats

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import com.example.teacheats.databinding.FragmentResultBinding
import java.io.File

var result: String = ""
var label: String = ""

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
        getLabel(binding)
        getImage(binding)
        binding.resultReturnButton.setOnClickListener { view: View ->
            deleteImage()
            view.findNavController().navigate(R.id.action_resultFragment_to_titleFragment)
        }
        binding.learnButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_resultFragment_to_foodNameFragment)
        }
        return binding.root
    }

    private fun getLabel(binding: FragmentResultBinding) {
        label = arguments?.getString("label").toString()
        if(label == ""){
            label = "None"
        }
        val myResult: TextView = binding.labelView
        myResult.text = label
    }
    //Finds image
    private fun getImage(binding: FragmentResultBinding) {
        result = arguments?.getString("photoPath").toString()
        if (result == "") {
            return
        }
        val imgFile = File(result)
        if (imgFile.exists()) {
            val myImage: ImageView = binding.foodPicture
            myImage.setImageURI(Uri.fromFile(imgFile))
        }
    }

    //Delete image when choosing new one
    private fun deleteImage() {
        result = arguments?.getString("photoPath").toString()
        if (result == "") {
            return
        }
        //Deletes image
        val imageFile = File(result)
        val deleted = imageFile.delete()
        if (!deleted) {
            Log.d("Error", "Not deleted")
        }
    }
}