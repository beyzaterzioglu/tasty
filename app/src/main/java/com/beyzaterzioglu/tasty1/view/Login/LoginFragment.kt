package com.beyzaterzioglu.tasty1.view.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.beyzaterzioglu.tasty1.R
import com.beyzaterzioglu.tasty1.util.Util
import com.beyzaterzioglu.tasty1.databinding.FragmentLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }


        binding.btnSignin.setOnClickListener {
            login()
        }

    }


    private fun login(){
        CoroutineScope(Dispatchers.IO).launch {
            val user = Util.auth.signInWithEmailAndPassword(binding.etUserName.text.toString(), binding.etPassword.text.toString()).await()
            if (user != null) {
                CoroutineScope(Dispatchers.Main).launch {

                    findNavController().navigate(R.id.action_loginFragment_to_home)
                }
            } else {
                Toast.makeText(requireContext(), "Hata", Toast.LENGTH_SHORT).show()
            }
        }
    }


}