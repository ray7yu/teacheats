package com.teach.eats.fragments.loading

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.teach.eats.functions.Clarifai
import com.teach.eats.R
import com.teach.eats.databinding.FragmentLoadingBinding

class LoadingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Inflates and binds layout to fragment
        val binding = DataBindingUtil.inflate<FragmentLoadingBinding>(
            inflater,
            R.layout.fragment_loading,
            container,
            false
        )
        val bundle = Bundle()
        val path = arguments?.getString("photoPath").toString()
        bundle.putString("photoPath", path)
        val apiCall = Clarifai(getApiSecret(), binding, bundle)
        apiCall.execute("Hi")
        return binding.root
    }

    init {
        System.loadLibrary("native-lib")
    }

    //Native C++ functions that contain API ID and Secret
    external fun getApiSecret(): String
    external fun getApiId(): String
}
