package com.example.teacheats

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.teacheats.databinding.FragmentResultBinding
import java.io.File

class Photo {
    companion object {
        fun getLabel(binding: FragmentResultBinding, arguments: Bundle?) {
            var label = arguments?.getString("label").toString()
            if(label == ""){
                label = "None"
            }
            val myResult: TextView = binding.labelView
            myResult.text = label
        }
        //Finds image
        fun getImage(binding: FragmentResultBinding, arguments: Bundle?) {
            val result = arguments?.getString("photoPath").toString()
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
        fun deleteImage(arguments: Bundle?) {
            val result = arguments?.getString("photoPath").toString()
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
}