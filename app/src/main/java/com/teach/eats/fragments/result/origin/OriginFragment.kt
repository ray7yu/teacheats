package com.teach.eats.fragments.result.origin

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.teach.eats.functions.Learn
import com.teach.eats.functions.Photo
import com.teach.eats.R
import com.teach.eats.databinding.FragmentOriginBinding

class OriginFragment : Fragment() {
    private lateinit var results : Bundle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Inflates and binds layout to fragment
        val binding = DataBindingUtil.inflate<FragmentOriginBinding>(
            inflater,
            R.layout.fragment_origin,
            container,
            false
        )
        results = setResult(savedInstanceState)

        //Custom back pressed callback that also deletes photo
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d(ContentValues.TAG, "Fragment back pressed invoked")
                    Photo.deleteImage(results)
                    if (isEnabled) {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            }
        )
        //Sets origin picture
        Learn.chooseOrigin(
            binding.shimmerFoodPic,
            binding.foodPic,
            results.getString("label").toString()
        )
        //Navigation for buttons
        binding.leftButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_originFragment_to_colorFragment, results)
        }
        binding.returnButton.setOnClickListener { view: View ->
            Photo.deleteImage(results)
            view.findNavController().navigate(R.id.action_originFragment_to_titleFragment)
        }
        return binding.root
    }

    //Saves the arguments when fragment is stopped
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("label", results.getString("label"))
        outState.putString("photoPath", results.getString("photoPath"))
    }

    //Restores or initializes the arguments when fragment is started
    private fun setResult(savedInstanceState: Bundle?) : Bundle {
        val result = Bundle()
        if(savedInstanceState != null) {
            result.putString("label", savedInstanceState.getString("label", ""))
            result.putString("photoPath", savedInstanceState.getString("photoPath", ""))
        } else {
            result.putString("label", requireArguments().getString("label", ""))
            result.putString("photoPath", requireArguments().getString("photoPath", ""))
        }
        return result
    }
}