package com.example.teacheats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.teacheats.databinding.FragmentFoodNameBinding

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
        Learn.chooseIcon(binding.foodPic, arguments?.getString("label").toString())
        Learn.chooseWord(binding.foodNameView, arguments?.getString("label").toString())
        this.context?.let { Learn.setFoodSound(it, binding.listenButton, arguments?.getString("label").toString()) }
        binding.leftButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_foodNameFragment_to_resultFragment, arguments)
        }
        binding.rightButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_foodNameFragment_to_colorFragment, arguments)
        }
        binding.returnButton.setOnClickListener { view: View ->
            Photo.deleteImage(arguments)
            view.findNavController().navigate(R.id.action_foodNameFragment_to_titleFragment)
        }
        return binding.root
    }
}