package com.teach.eats.fragments.title

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.teach.eats.R
import com.teach.eats.databinding.FragmentTitleBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class TitleFragment : Fragment() {
    //Takes picture and has callback for result
    private val getPicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                val newBundle = Bundle()
                newBundle.putString("photoPath", currentPhotoPath)
                Log.d("photoPath", currentPhotoPath)
                view?.findNavController()
                    ?.navigate(R.id.action_titleFragment_to_loadingFragment, newBundle)

            } else {
                Log.d("Error", "Error")
            }
        }

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
        binding.startButton.setOnClickListener {
            takeImage()
        }
        return binding.root
    }

    lateinit var currentPhotoPath: String

    //Creates a file and filepath for the image
    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Creates image file name
        val timeStamp: String = SimpleDateFormat.getDateTimeInstance().format(Date())
        val storageDir: File? = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//        val storageDir: File? = activity?.getExternalStorageDirectory(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a filepath for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    //Calls function to creates file and calls function to take picture
    private fun takeImage() {
        val photoFile: File? = try {
            createImageFile()
        } catch (ex: IOException) {
            // Error occurred while creating the File
            Log.d("Error", "Error")
            null
        }
        photoFile?.also {
            val photoURI: Uri = FileProvider.getUriForFile(
                requireActivity(),
                "com.teach.eats.fileprovider",
                it
            )
            getPicture.launch(photoURI)
        }
    }
}
