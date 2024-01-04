package com.beyzaterzioglu.tasty1.view.Home

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.beyzaterzioglu.tasty1.R
import com.beyzaterzioglu.tasty1.databinding.FragmentHomeBinding
import com.beyzaterzioglu.tasty1.model.RecipeItem


class HomeFragment : Fragment(R.layout.fragment_home) {


    lateinit var binding : FragmentHomeBinding

    lateinit var viewModel: HomeViewModel

    lateinit var  adapter : RecipeRecyclerAdapter

    var recipeList = arrayListOf<RecipeItem>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        binding = FragmentHomeBinding.bind(view)

        adapter = RecipeRecyclerAdapter(requireContext())

        initRecyclerView()
    }


    private fun initRecyclerView(){

        binding.foodRecycler.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(
            binding.foodRecycler.context,
            LinearLayoutManager.VERTICAL
        )
        binding.foodRecycler.addItemDecoration(dividerItemDecoration)

        adapter.setOnItemClickListener {
            //yeni fragment Aç
            val bundle = bundleOf("id" to it.id)
            findNavController().navigate(R.id.action_home_to_recipeDetailFragment, bundle)
        }

        viewModel.recipeList.observe(viewLifecycleOwner){
            adapter.recipes = it
            recipeList.addAll(it)
        }


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                filterList(p0!!)
                return true
            }
        })


    }

    private fun filterList(query : String?){
        if (query != null){
            val filteredList = arrayListOf<RecipeItem>()

            for (i in recipeList){
                if (i.title.lowercase().contains(query)){
                    filteredList.add(i)
                }
            }

            if (filteredList.isNotEmpty()){
                adapter.recipes = filteredList
            }
        }
    }



}