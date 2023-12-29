package com.beyzaterzioglu.tasty1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.beyzaterzioglu.tasty1.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity () {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        NavigationUI.setupWithNavController(
            binding.bottomNav,
            navHostFragment.navController
        )

        navHostFragment.navController.addOnDestinationChangedListener{_, destination, _ ->


            when(destination.id)
            {
                R.id.home -> {
                    binding.bottomNav.visibility = View.VISIBLE
                }
                R.id.favorities -> {
                    binding.bottomNav.visibility = View.VISIBLE
                }
                R.id.profile -> {
                    binding.bottomNav.visibility = View.VISIBLE
                }
                R.id.recipeDetailFragment -> {
                    binding.bottomNav.visibility = View.GONE
                }
                R.id.getStartedFragment -> {
                    binding.bottomNav.visibility = View.GONE
                }
                R.id.loginFragment -> {
                    binding.bottomNav.visibility = View.GONE
                }
                R.id.registerFragment -> {
                    binding.bottomNav.visibility = View.GONE
                }
                else -> {
                    binding.bottomNav.visibility = View.VISIBLE
                }



            }
            true

        }

    }


}