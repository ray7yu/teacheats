package com.teach.eats.fragments.result

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
import com.teach.eats.Learn
import com.teach.eats.Photo
import com.teach.eats.R
import com.teach.eats.databinding.FragmentColorBinding

class ColorFragment : Fragment() {
    private lateinit var results : Bundle
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
        results = Bundle()
        if(savedInstanceState != null) {
            results.putString("label", savedInstanceState.getString("label", ""))
            results.putString("photoPath", savedInstanceState.getString("photoPath", ""))
        } else {
            results.putString("label", requireArguments().getString("label", ""))
            results.putString("photoPath", requireArguments().getString("photoPath", ""))
        }
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
        this.context?.let {
            Learn.chooseText(
                it,
                binding.colorView,
                results.getString("label").toString(),
                1
            )
        }
        this.context?.let {
            Learn.setSound(
                it,
                binding.listenButton,
                results.getString("label").toString(),
                1
            )
        }
        binding.leftButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_colorFragment_to_foodNameFragment, results)
        }
        binding.rightButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_colorFragment_to_originFragment, results)
        }
        binding.returnButton.setOnClickListener { view: View ->
            Photo.deleteImage(results)
            view.findNavController().navigate(R.id.action_colorFragment_to_titleFragment)
        }
        return binding.root
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("label", results.getString("label"))
        outState.putString("photoPath", results.getString("photoPath"))
    }
}