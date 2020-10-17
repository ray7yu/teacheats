package com.teach.eats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
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
        Photo.getLabel(binding, arguments)
        Photo.getImage(binding, arguments)

        binding.resultReturnButton.setOnClickListener { view: View ->
            Photo.deleteImage(arguments)
            view.findNavController().navigate(R.id.action_resultFragment_to_titleFragment)
        }
        binding.learnButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_resultFragment_to_foodNameFragment, arguments)
        }
        if(arguments?.getString("label").toString() == ""){
            binding.learnButton.visibility = View.INVISIBLE
        }
        return binding.root
    }
}