package com.teach.eats.fragments.result

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.teach.eats.Photo
import com.teach.eats.R
import com.teach.eats.databinding.FragmentResultBinding
import okio.buffer
import okio.sink
import java.io.IOException
import java.io.OutputStream


class ResultFragment : Fragment() {
    private lateinit var results : Bundle
    @RequiresApi(Build.VERSION_CODES.Q)
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
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun savePicture(){
        val photoPath = results.getString("photoPath").toString()
        Log.i("Path", photoPath)
        //Requests permission

        //Saves image to gallery
        val bitmap = BitmapFactory.decodeFile(photoPath, null)
        val ei = ExifInterface(photoPath)
        val orientation: Int = ei.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_UNDEFINED
        )
        val rotatedBitmap = when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> Photo.rotateImage(bitmap, 90F)
            ExifInterface.ORIENTATION_ROTATE_180 -> Photo.rotateImage(
                bitmap,
                180F
            )
            ExifInterface.ORIENTATION_ROTATE_270 -> Photo.rotateImage(
                bitmap,
                270F
            )
            ExifInterface.ORIENTATION_NORMAL -> bitmap
            else -> bitmap
        }

        val relativeLocation = Environment.DIRECTORY_PICTURES + "/teacheats/"
        val contentValues = ContentValues().apply {
            put(
                MediaStore.Images.Media.DISPLAY_NAME,
                System.currentTimeMillis().toString() + " : Fruit.jpg"
            )
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, relativeLocation)
        }
        val resolver = context?.contentResolver
        var stream : OutputStream? = null
        var uri : Uri? = null
        try {
            val contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            if (resolver != null) {
                uri = resolver.insert(contentUri, contentValues)!!
            }
            if (uri == null) {
                throw IOException("Failed to create new MediaStore record.")
            }
            if (resolver != null) {
                stream = resolver.openOutputStream(uri)!!
            }
            if (stream == null) {
                throw IOException("Failed to get output stream.")
            }
            if (!rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)) {
                throw IOException("Failed to save bitmap.")
            }
        } catch (e: IOException) {
            if (uri != null) {
                // Don't leave an orphan entry in the MediaStore
                resolver?.delete(uri, null, null)
            }
            throw e
        } finally {
            stream?.close()
        }
    }
}