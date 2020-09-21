package com.example.teacheats

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.example.teacheats.databinding.FragmentTitleBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class TitleFragment : Fragment() {
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_TAKE_PHOTO = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Inflates and binds layout to fragment
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(
            inflater,
            R.layout.fragment_title,
            container,
            false
        )
        //Binds listeners to buttons
        binding.helpButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_helpFragment)
        }
        binding.startButton.setOnClickListener { view: View ->
            dispatchTakePictureIntent()
            setFragmentResult("requestKey", bundleOf("bundleKey" to currentPhotoPath))
            view.findNavController().navigate(R.id.action_titleFragment_to_resultFragment)
        }
        return binding.root
    }

    lateinit var currentPhotoPath: String

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Creates image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a filepath for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            //ensure that there's a camera activity to handle the intent
            activity?.packageManager?.let {
                takePictureIntent.resolveActivity(it)?.also {
                    // Create the File where the photo should go
                    val photoFile: File? = try {
                        createImageFile()
                    } catch (ex: IOException) {
                        // Error occurred while creating the File
                        Log.d("Error", "Error")
                        null
                    }
                    // Continue only if the File was successfully created
                    photoFile?.also {
                        val photoURI: Uri = FileProvider.getUriForFile(requireActivity(), "com.example.android.fileprovider", it)
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//                        REQUEST_TAKE_PHOTO
                        Log.d("Picture", "is working")
                        startActivity(takePictureIntent)
                    }
                }
            }
        }
    }
}
