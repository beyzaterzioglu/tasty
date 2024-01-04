package com.beyzaterzioglu.tasty1.view.GetStarted

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.beyzaterzioglu.tasty1.R
import com.beyzaterzioglu.tasty1.util.Util
import com.beyzaterzioglu.tasty1.databinding.FragmentGetStartedBinding
import com.beyzaterzioglu.tasty1.util.PrefUtil

class GetStartedFragment : Fragment(R.layout.fragment_get_started) {


    private lateinit var binding: FragmentGetStartedBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGetStartedBinding.bind(view)

        val isStarted = PrefUtil.getBooleanPrefence(requireContext(), "getStarted", false)

        if (isStarted){
            binding.isStartedLayout.visibility = View.GONE
            binding.splashLogo.visibility = View.VISIBLE
        } else {
            binding.isStartedLayout.visibility = View.VISIBLE
            binding.splashLogo.visibility = View.GONE
        }


        binding.btnGetStarted.setOnClickListener {
            PrefUtil.writeBoolean(requireContext(), "getStarted", true)
            findNavController().navigate(R.id.action_getStartedFragment_to_loginFragment)
        }

        Handler().postDelayed({
            if (Util.auth.currentUser != null) {
                lifecycleScope.launchWhenResumed {
                    findNavController().navigate(R.id.action_getStartedFragment_to_home)
                }
            } else{
                lifecycleScope.launchWhenResumed {
                    findNavController().navigate(R.id.action_getStartedFragment_to_loginFragment)
                }
            }
        }, 3000)
    }

}