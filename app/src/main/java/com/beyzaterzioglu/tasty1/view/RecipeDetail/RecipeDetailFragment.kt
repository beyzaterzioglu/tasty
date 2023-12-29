package com.beyzaterzioglu.tasty1.view.RecipeDetail

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
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


        binding.addfavoritesbutton.setOnClickListener {
            item.isFav = !item.isFav
            setFav(item)

            viewModel.saveFav(requireContext(), item)

        }

        viewModel.recipeModel.observe(viewLifecycleOwner) {
            it.let {
                item = it

                setFav(it)

                binding.recipeName.text = it.title

                Glide.with(requireContext()).load(it.image).into(binding.recipeImage)

                /*val ingredientsText = it.extendedIngredients?.map { ingredient ->
                    ingredient.name
                }?.joinToString("\n")*/

                val summaryString: String = Html
                    .fromHtml(it.summary, Html.FROM_HTML_MODE_COMPACT)
                    .toString()
                val infoString: String = Html
                    .fromHtml(it.instructions, Html.FROM_HTML_MODE_COMPACT)
                    .toString()
                binding.recipeIngredients.text = summaryString
                binding.recipeInfo.text = infoString
                binding.recipeTime.text = it.readyInMinutes.toString()
                binding.recipeServe.text = it.servings.toString()

                /*
                                binding.addfavoritesbutton.setOnClickListener {
                                    if (::viewModel.isInitialized) {
                                        viewModel.recipeModel.value?.let { recipe ->
                                            val favoriteRecipe = RecipeItem(
                                                id = recipe.id,

                                                title = recipe.title,
                                                image = recipe.image,
                                             //   val favoritesRecyclerAdapter = favoritesRecyclerAdapter(requireContext())
                                            //    val success = favoritesRecyclerAdapter.addFavorities(favoriteRecipe)

                                            if (success) {
                                            Toast.makeText(requireContext(), "Favorilere Eklendi", Toast.LENGTH_SHORT).show()
                                           } else {
                                               Toast.makeText(requireContext(), "Favorilere Eklenemedi", Toast.LENGTH_SHORT).show()
                                            }

                                        }
                                    }
                                }
                               */

            }


        }
    }

    fun setFav(recipeDetail: RecipeDetail?){
        if(recipeDetail != null && !recipeDetail.isFav){
            binding.addfavoritesbutton.setImageResource(R.drawable.addfavoritebutton)
        } else {
            binding.addfavoritesbutton.setImageResource(R.drawable.fav_button)
        }
    }
}
