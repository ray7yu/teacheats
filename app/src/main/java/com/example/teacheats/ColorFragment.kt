package com.example.teacheats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.teacheats.databinding.FragmentColorBinding

class ColorFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Inflates and binds layout to fragment
        val binding = DataBindingUtil.inflate<FragmentColorBinding>(
            inflater,
            R.layout.fragment_color,
            container,
            false
        )
        binding.leftButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_colorFragment_to_foodNameFragment)
        }
        binding.rightButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_colorFragment_to_originFragment)
        }
        return binding.root
    }
}