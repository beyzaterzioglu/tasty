package com.beyzaterzioglu.tasty1.view.Profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.beyzaterzioglu.tasty1.R
import com.beyzaterzioglu.tasty1.util.Util
import com.beyzaterzioglu.tasty1.databinding.FragmentProfileBinding
import com.beyzaterzioglu.tasty1.model.UserInfo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfileFragment : Fragment(R.layout.fragment_profile) {


    private lateinit var binding: FragmentProfileBinding
    private var database: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var userModel = UserInfo(
        id = "",
        userEmail = "",
        userName = "",
        phoneNumber = ""
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)

        binding.updateUser.setOnClickListener {
            updateUser()
        }

        getUserInfo()
    }


    private fun getUserInfo() {
        CoroutineScope(Dispatchers.IO).launch {
            val user =
                database.collection("users").document(Util.auth.currentUser!!.uid).get().await()
            if (user != null) {
                userModel = UserInfo(
                    id = user["id"].toString(),
                    userEmail = user["userEmail"].toString(),
                    userName = user["userName"].toString(),
                    phoneNumber = user["phoneNumber"].toString()
                )

                binding.tvUsername.text = userModel.userName
                binding.etEmail.setText(userModel.userEmail)
                binding.etUsername.setText(userModel.userName)
                binding.etPhoneNumber.setText(userModel.phoneNumber)
                //userInfoDao.insertUsers(userModel)
            }
        }
    }

    private fun updateUser() {
        CoroutineScope(Dispatchers.IO).launch {
            if (userModel.id != ""){

                database.collection("users").document(userModel.id).set(userModel).await()
                Toast.makeText(requireContext(), "bilgiler güncellendi.", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(requireContext(), "Hata", Toast.LENGTH_SHORT).show()
            }

        }
    }

}