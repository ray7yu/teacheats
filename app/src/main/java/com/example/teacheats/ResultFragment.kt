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
        val binding = DataBindingUtil.inflate<FragmentResultBinding>(inflater, R.layout.fragment_result, container, false)
        getImage(binding)
        binding.resultReturnButton.setOnClickListener{view: View ->
            deleteImage(binding)
            view.findNavController().navigate(R.id.action_resultFragment_to_titleFragment)
        }
        return binding.root
    }
    //Finds image
    private fun getImage(binding : FragmentResultBinding) {
        result = arguments?.getString("photoPath").toString()
        if (result == "") {
            return
        }
        val imgFile =  File(result)
        if(imgFile.exists()){
            val myImage : ImageView = binding.foodPicture;
            myImage.setImageURI(Uri.fromFile(imgFile))
        }
    }
    //Delete image when choosing new one
    private fun deleteImage(binding : FragmentResultBinding) {
        result = arguments?.getString("photoPath").toString()
        if (result == ""){
            return
        }
        //Deletes image
        val imageFile = File(result)
        val deleted = imageFile.delete()
        if(!deleted){
            Log.d("Error", "Not deleted")
        }
    }
}