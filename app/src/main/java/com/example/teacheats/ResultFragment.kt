package com.example.teacheats

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import com.example.teacheats.databinding.FragmentResultBinding
import java.io.File
var result : String = ""
class ResultFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Use the Kotlin extension in the fragment-ktx artifact
        setFragmentResultListener("requestKey") { key, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            result = bundle.getString("bundleKey").toString()
        }
        val binding = DataBindingUtil.inflate<FragmentResultBinding>(inflater, R.layout.fragment_result, container, false)
        getImage(binding)
        binding.resultReturnButton.setOnClickListener{view: View ->
            view.findNavController().navigate(R.id.action_resultFragment_to_titleFragment)
        }
        return binding.root
    }
    //Finds image
    private fun getImage(binding : FragmentResultBinding) {
        if (result == "") {
            return
        }
        val imgFile =  File(result)
        if(imgFile.exists()){
            val myImage : ImageView = binding.foodPicture;
            myImage.setImageURI(Uri.fromFile(imgFile))
        }
    }
}