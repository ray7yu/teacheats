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
import com.teach.eats.databinding.FragmentFoodNameBinding

class FoodNameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Inflates and binds layout to fragment
        val binding = DataBindingUtil.inflate<FragmentFoodNameBinding>(
            inflater,
            R.layout.fragment_food_name,
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
        //Sets up food picture and food name views
        Learn.chooseIcon(binding.foodPic, arguments?.getString("label").toString())
        Learn.chooseWord(binding.foodNameView, arguments?.getString("label").toString())

        //Sets up audio for listen button
        this.context?.let {
            Learn.setFoodSound(
                it,
                binding.listenButton,
                arguments?.getString("label").toString()
            )
        }

        //Navigation for buttons
        binding.leftButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_foodNameFragment_to_resultFragment, arguments)
        }
        binding.rightButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_foodNameFragment_to_colorFragment, arguments)
        }
        binding.returnButton.setOnClickListener { view: View ->
            Photo.deleteImage(arguments)
            view.findNavController().navigate(R.id.action_foodNameFragment_to_titleFragment)
        }
        return binding.root
    }
}