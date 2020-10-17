package com.teach.eats.fragments.result

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.teach.eats.Photo
import com.teach.eats.R
import com.teach.eats.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
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
        //Custom back pressed callback that also deletes photo
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d(TAG, "Fragment back pressed invoked")
                    Photo.deleteImage(arguments)
                    if (isEnabled) {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            }
        )

        //Label and Image are set using Photo class
        Photo.getLabel(binding, arguments)
        Photo.getImage(binding, arguments)

        //Pressing return button will delete image and return to title fragment
        binding.resultReturnButton.setOnClickListener { view: View ->
            Photo.deleteImage(arguments)
            view.findNavController().navigate(R.id.action_resultFragment_to_titleFragment)
        }
        //Pressing learn will go to food name fragment, while passing bundle
        binding.learnButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_resultFragment_to_foodNameFragment, arguments)
        }
        //If no label, learn button is not visible
        if (arguments?.getString("label").toString() == "") {
            binding.learnButton.visibility = View.INVISIBLE
        }
        return binding.root
    }
}