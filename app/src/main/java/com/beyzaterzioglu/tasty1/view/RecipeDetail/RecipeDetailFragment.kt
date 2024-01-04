package com.beyzaterzioglu.tasty1.view.RecipeDetail

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.beyzaterzioglu.tasty1.R

import com.beyzaterzioglu.tasty1.databinding.FragmentRecipeDetailBinding
import com.beyzaterzioglu.tasty1.model.RecipeDetail
import com.bumptech.glide.Glide

class RecipeDetailFragment : Fragment(R.layout.fragment_recipe_detail) {

    lateinit var binding: FragmentRecipeDetailBinding
    lateinit var viewModel: RecipeDetailViewModel

    lateinit var item: RecipeDetail

    var isFav = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(requireActivity())[RecipeDetailViewModel::class.java]
        binding = FragmentRecipeDetailBinding.bind(view)

        val id = arguments?.getInt("id")


        viewModel.makeApiCall(id!!)

        viewModel.recipeModel.observe(viewLifecycleOwner) {
            item = it
            if (arguments?.containsKey("isFav") == true) {
                it.isFav = true
            } else {
                setValues(it)
            }

            viewModel.fetchById(requireContext(), id)

            viewModel.favoritiesList.observe(viewLifecycleOwner) {

                if (!it.isNullOrEmpty() && item != null) {
                    for (favItem in it) {
                        if (favItem.id == id){
                            item.isFav = true
                            setValues(item)
                            break
                        }
                    }
                }
            }
        }




        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }




        binding.addfavoritesbutton.setOnClickListener {
            item.isFav = !item.isFav
            setFav(item)
            if (item.isFav) {

                viewModel.saveFav(requireContext(), item)
            } else {
                viewModel.deleteFav(requireContext(), item)
            }

        }

    }


    fun setValues(recipeDetail: RecipeDetail) {
        item = recipeDetail

        setFav(recipeDetail)

        binding.recipeName.text = recipeDetail.title

        Glide.with(requireContext()).load(recipeDetail.image).into(binding.recipeImage)

        val summaryString: String = Html
            .fromHtml(recipeDetail.summary, Html.FROM_HTML_MODE_COMPACT)
            .toString()
        val infoString: String = Html
            .fromHtml(recipeDetail.instructions, Html.FROM_HTML_MODE_COMPACT)
            .toString()
        binding.recipeIngredients.text = summaryString
        binding.recipeInfo.text = infoString
        binding.recipeTime.text = recipeDetail.readyInMinutes.toString()
        binding.recipeServe.text = recipeDetail.servings.toString()
    }

    private fun setFav(recipeDetail: RecipeDetail?) {
        if (recipeDetail != null && !recipeDetail.isFav) {
            binding.addfavoritesbutton.setImageResource(R.drawable.addfavoritebutton)
        } else {
            binding.addfavoritesbutton.setImageResource(R.drawable.fav_button)
        }
    }
}
