package com.beyzaterzioglu.tasty1.view.Register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.beyzaterzioglu.tasty1.R
import com.beyzaterzioglu.tasty1.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(R.layout.fragment_register) {

    lateinit var binding: FragmentRegisterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterBinding.bind(view)
    }

}