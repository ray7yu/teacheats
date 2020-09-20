package com.example.teacheats

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.teacheats.databinding.ActivityMainBinding
import com.example.teacheats.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TitleFragment : Fragment() {
    private val REQUEST_IMAGE_CAPTURE = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflates and binds layout to fragment
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater, R.layout.fragment_title, container, false)
        //Binds listeners to buttons
        binding.helpButton.setOnClickListener{view: View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_helpFragment)
        }
        binding.startButton.setOnClickListener{view: View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_resultFragment)
        }
        return binding.root
    }
    private fun dispatchTakePictureIntent() {
        PackageManager packageManager = getActivity().getPackageManager()
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }
}