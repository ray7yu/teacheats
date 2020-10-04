package com.example.teacheats

import android.net.Uri
import android.os.AsyncTask
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
import com.clarifai.channel.ClarifaiChannel
import com.clarifai.credentials.ClarifaiCallCredentials
import com.clarifai.grpc.api.*
import com.clarifai.grpc.api.status.StatusCode
import com.example.teacheats.databinding.FragmentTitleBinding
import io.grpc.Channel
import java.io.File
import java.io.IOException
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*

class TitleFragment : Fragment() {
    //Takes picture and has callback for result
    var apiResult : PostWorkflowResultsResponse? = null
    private val getPicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                val newBundle = Bundle()
                newBundle.putString("photoPath", currentPhotoPath)
                Log.d("photoPath", currentPhotoPath)
//                val apiCall = Clarifai(getApiSecret(), view, newBundle)
//                apiCall.execute("Hi")
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
                "com.example.android.fileprovider",
                it
            )
            getPicture.launch(photoURI)
        }
    }

//    companion object {
//        //Replace first parameter in async task with bitmap
//        class Clarifai(private val apiKey: String, private val view: View?, private val bundle: Bundle) : AsyncTask<String, String, Boolean>() {
//            override fun onPreExecute() {
//                //Move to spinner
//            }
//
//            override fun doInBackground(vararg params: String?): Boolean {
//                val channel: Channel = ClarifaiChannel.INSTANCE.jsonChannel;
//                val stub = V2Grpc.newBlockingStub(channel)
//                    .withCallCredentials(ClarifaiCallCredentials(apiKey))
//                val postWorkflowResultsResponse = stub.postWorkflowResults(
//                    PostWorkflowResultsRequest.newBuilder()
//                        .setWorkflowId("Food")
//                        .addInputs(
//                            Input.newBuilder().setData(
//                                Data.newBuilder().setImage(
//                                    Image.newBuilder().setUrl(
//                                        "https://samples.clarifai.com/metro-north.jpg"
//                                    )
//                                )
//                            )
//                        )
//                        .build()
//                )
//                if (postWorkflowResultsResponse.status.code != StatusCode.SUCCESS) {
//                    println("Post workflow results failed, status: " + postWorkflowResultsResponse.status)
//                    return false
////                    throw java.lang.RuntimeException("Post workflow results failed, status: " + postWorkflowResultsResponse.status)
//                }
//
//                val results = postWorkflowResultsResponse.getResults(0)
//
//                for (output in results.outputsList) {
//                    val model: Model = output.model
//                    println(
//                        "Predicted concepts for the model `" + model.name.toString() + "`:"
//                    )
//                    for (concept in output.data.conceptsList) {
//                        System.out.printf("\t%s %.2f%n", concept.name, concept.value)
//                    }
//                }
//                return true
//            }
//
//            override fun onPostExecute(result: Boolean?) {
//                //Go to result fragment
//                view?.findNavController()
//                    ?.navigate(R.id.action_titleFragment_to_resultFragment, bundle)
//            }
//        }
//    }
//
//    init {
//        System.loadLibrary("native-lib")
//    }
//
//    external fun getApiSecret(): String
//    external fun getApiId(): String
}
