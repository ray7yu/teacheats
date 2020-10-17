package com.teach.eats

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
import com.teach.eats.databinding.FragmentOriginBinding

class OriginFragment : Fragment() {
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
        //Custom back pressed callback that also deletes photo
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d(ContentValues.TAG, "Fragment back pressed invoked")
                    Photo.deleteImage(arguments)
                    if (isEnabled) {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            }
        )
        //Sets origin picture
        Learn.chooseOrigin(
            binding.foodPic,
            arguments?.getString("label").toString()
        )
        //Navigation for buttons
        binding.leftButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_originFragment_to_colorFragment, arguments)
        }
        binding.returnButton.setOnClickListener { view: View ->
            Photo.deleteImage(arguments)
            view.findNavController().navigate(R.id.action_originFragment_to_titleFragment)
        }
        return binding.root
    }
}