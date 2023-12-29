package com.beyzaterzioglu.tasty1.view.Favorities

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.beyzaterzioglu.tasty1.R
import com.beyzaterzioglu.tasty1.view.RecipeDetail.RecipeDetailRecyclerAdapter
import com.beyzaterzioglu.tasty1.databinding.FragmentFavoritiesBinding
import com.beyzaterzioglu.tasty1.model.RecipeDetail


class FavoritiesFragment : Fragment(R.layout.fragment_favorities) {
private lateinit var binding: FragmentFavoritiesBinding
private lateinit var viewModel: FavoritiesViewModel
private lateinit var adapter: RecipeDetailRecyclerAdapter


private  var favoritiesList = arrayListOf<RecipeDetail>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[FavoritiesViewModel::class.java]

        binding = FragmentFavoritiesBinding.bind(view)


        adapter = RecipeDetailRecyclerAdapter(requireContext())

        binding.foodRecyclerFavorities.adapter = adapter

        viewModel.favoritiesList.observe(viewLifecycleOwner){
            adapter.recipes = it
            favoritiesList.addAll(it)
        }

        adapter.setOnItemClickListener {
            //yeni fragment Aç
            val bundle = bundleOf("id" to it.id)
            Toast.makeText(requireContext(), it.title, Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_home_to_recipeDetailFragment, bundle)
        }


        binding.searchViewFavorities.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                filterList(p0!!)
                return true
            }
        })

    viewModel.fetchFavItems(requireContext())

    }



    private fun filterList(query : String?){
        if (query != null){
            val filteredList = arrayListOf<RecipeDetail>()

            for (i in favoritiesList){
                if (i.title?.lowercase()!!.contains(query)){
                    filteredList.add(i)
                }
            }

            if (filteredList.isNotEmpty()){
                adapter.recipes = favoritiesList
            }
        }
    }

}