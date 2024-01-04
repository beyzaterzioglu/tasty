package com.beyzaterzioglu.tasty1.view.Register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.beyzaterzioglu.tasty1.R
import com.beyzaterzioglu.tasty1.util.Util
import com.beyzaterzioglu.tasty1.databinding.FragmentRegisterBinding
import com.beyzaterzioglu.tasty1.model.UserInfo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private var database: FirebaseFirestore =FirebaseFirestore.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterBinding.bind(view)


        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }


        binding.btnSignUp.setOnClickListener {
            registerUser(UserInfo("", binding.etEmail.text.toString(), binding.etUsername.text.toString(), binding.etPhoneNumber.text.toString()))
        }
    }


    private fun registerUser(userInfo : UserInfo){
        CoroutineScope(Dispatchers.IO).launch {

            val result = Util.auth.createUserWithEmailAndPassword(userInfo.userEmail, binding.etPassword.text.toString()).await()
            if (result.user != null){
                userInfo.id = result.user!!.uid
                database.collection("users").document(userInfo.id).set(userInfo).await()
                CoroutineScope(Dispatchers.Main).launch {

                    Toast.makeText(requireContext(), "Kayıt Oluşturuldu.", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }

            }else{
                Toast.makeText(requireContext(), "Hata Oluştu.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}