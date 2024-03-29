package com.teach.eats.functions

import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import com.clarifai.channel.ClarifaiChannel
import com.clarifai.credentials.ClarifaiCallCredentials
import com.clarifai.grpc.api.*
import com.clarifai.grpc.api.status.StatusCode
import com.teach.eats.databinding.FragmentLoadingBinding
import com.google.protobuf.ByteString
import com.teach.eats.R
import io.grpc.Channel
import java.io.File
import java.nio.file.Files

//Asynchronously makes a call to the Clarifai API in order to classify the fruit image and obtain a label.
class Clarifai(
    private val apiKey: String,
    private val binding: FragmentLoadingBinding,
    private val bundle: Bundle
) : AsyncTask<String, String, Output>() {
    override fun onPreExecute() {

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun doInBackground(vararg params: String?): Output? {
        val channel: Channel = ClarifaiChannel.INSTANCE.jsonChannel;
        val stub = V2Grpc.newBlockingStub(channel)
            .withCallCredentials(ClarifaiCallCredentials(apiKey))
        val postWorkflowResultsResponse = stub.postWorkflowResults(
            PostWorkflowResultsRequest.newBuilder()
                .setWorkflowId("Food")
                .addInputs(
                    Input.newBuilder().setData(
                        Data.newBuilder().setImage(
                            Image.newBuilder()
                                .setBase64(
                                    ByteString.copyFrom(Files.readAllBytes(
                                        File(bundle.getString("photoPath").toString()).toPath()
                                )))
                        )
                    )
                )
                .build()
        )
        if (postWorkflowResultsResponse.status.code != StatusCode.SUCCESS) {
            println("Post workflow results failed, status: " + postWorkflowResultsResponse.status)
            return null
//                    throw java.lang.RuntimeException("Post workflow results failed, status: " + postWorkflowResultsResponse.status)
        }

        val results = postWorkflowResultsResponse.getResults(0)

        for (output in results.outputsList) {
            val model: Model = output.model
            println("Predicted concepts for the model `" + model.name.toString() + "`:")
            for (concept in output.data.conceptsList) {
                System.out.printf("\t%s %.2f%n", concept.name, concept.value)
            }
            return output
        }
        return null
    }


    override fun onPostExecute(result: Output) {
        //Go to result fragment
        var label = ""
        loop@ for (concept in result.data.conceptsList) {
            println(concept.name)
            when (concept.name) {
                "apple" -> {
                    label = "Apple!"
                    break@loop
                }
                "banana" -> {
                    label = "Banana!"
                    break@loop
                }
                "grape" -> {
                    label = "Grape!"
                    break@loop
                }
                "lemon" -> {
                    label = "Lemon!"
                    break@loop
                }
                "mango" -> {
                    label = "Mango!"
                    break@loop
                }
                "orange" -> {
                    label = "Orange!"
                    break@loop
                }
                "peach" -> {
                    label = "Peach!"
                    break@loop
                }
                "pineapple" -> {
                    label = "Pineapple!"
                    break@loop
                }
                "strawberry" -> {
                    label = "Strawberry!"
                    break@loop
                }
                "watermelon" -> {
                    label = "Watermelon!"
                    break@loop
                }
                //Post Midterm Fruits
                "avocado" -> {
                    label = "Avocado!"
                    break@loop
                }
                "blueberry" -> {
                    label = "Blueberry!"
                    break@loop
                }
                "cherry" -> {
                    label = "Cherry!"
                    break@loop
                }
                "coconut" -> {
                    label = "Coconut!"
                    break@loop
                }
                "dragonfruit" -> {
                    label = "Dragonfruit!"
                    break@loop
                }
                "durian" -> {
                    label = "Durian!"
                    break@loop
                }
                "grapefruit" -> {
                    label = "Grapefruit!"
                    break@loop
                }
                "guava" -> {
                    label = "Guava!"
                    break@loop
                }
                "kiwi fruit" -> {
                    label = "Kiwi!"
                    break@loop
                }
                "lime" -> {
                    label = "Lime!"
                    break@loop
                }
                "lychee" -> {
                    label = "Lychee!"
                    break@loop
                }
                "papaya" -> {
                    label = "Papaya!"
                    break@loop
                }
                "passionfruit" -> {
                    label = "Passionfruit!"
                    break@loop
                }
                "pear" -> {
                    label = "Pear!"
                    break@loop
                }
                "persimmon" -> {
                    label = "Persimmon!"
                    break@loop
                }
                "pomegranate" -> {
                    label = "Pomegranate!"
                    break@loop
                }
                "raspberry" -> {
                    label = "Raspberry!"
                    break@loop
                }
                else -> {
                    continue@loop
                }
            }
        }
        bundle.putString("label", label)
        println(binding.root)
        binding.root.findNavController()
            .navigate(R.id.action_loadingFragment_to_resultFragment, bundle)
    }
}