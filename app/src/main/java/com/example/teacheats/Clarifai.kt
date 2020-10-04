package com.example.teacheats

import android.os.AsyncTask
import android.os.Bundle
import androidx.navigation.findNavController
import com.clarifai.channel.ClarifaiChannel
import com.clarifai.credentials.ClarifaiCallCredentials
import com.clarifai.grpc.api.*
import com.clarifai.grpc.api.status.StatusCode
import com.example.teacheats.databinding.FragmentLoadingBinding
import io.grpc.Channel

class Clarifai(
    private val apiKey: String,
    private val binding: FragmentLoadingBinding,
    private val bundle: Bundle
) : AsyncTask<String, String, Output>() {
    override fun onPreExecute() {

    }

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
                            Image.newBuilder().setUrl(
                                "https://samples.clarifai.com/metro-north.jpg"
                            )
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
                    label = "Apple"
                    break@loop
                }
                "banana" -> {
                    label = "Banana"
                    break@loop
                }
                "grape" -> {
                    label = "Grape"
                    break@loop
                }
                "lemon" -> {
                    label = "Lemon"
                    break@loop
                }
                "mango" -> {
                    label = "Mango"
                    break@loop
                }
                "orange" -> {
                    label = "Orange"
                    break@loop
                }
                "peach" -> {
                    label = "Peach"
                    break@loop
                }
                "pineapple" -> {
                    label = "Pineapple"
                    break@loop
                }
                "strawberry" -> {
                    label = "Strawberry"
                    break@loop
                }
                "watermelon" -> {
                    label = "Watermelon"
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