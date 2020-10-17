package com.teach.eats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        Learn.chooseOrigin(
            binding.foodPic,
            arguments?.getString("label").toString()
        )
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