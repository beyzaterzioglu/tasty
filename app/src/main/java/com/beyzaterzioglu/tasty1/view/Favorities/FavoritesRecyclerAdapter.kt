package com.beyzaterzioglu.tasty1.view.Favorities


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.beyzaterzioglu.tasty1.R
import com.beyzaterzioglu.tasty1.databinding.LayoutRecipeItemBinding
import com.beyzaterzioglu.tasty1.model.RecipeItem
import com.bumptech.glide.Glide

class FavoritesRecyclerAdapter(
    private val context: Context
) : RecyclerView.Adapter<FavoritesRecyclerAdapter.FavoriteViewHolder>() {
    private var onClickListener: ((RecipeItem) -> Unit)? = null

    private val diffUtil = object : DiffUtil.ItemCallback<RecipeItem>() {
        override fun areContentsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var favoriteRecipes: List<RecipeItem>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    fun setOnItemClickListener(listener: (RecipeItem) -> Unit) {
        onClickListener = listener
    }

    class FavoriteViewHolder(private var recipeItemBinding: LayoutRecipeItemBinding) :
        RecyclerView.ViewHolder(recipeItemBinding.root) {

        fun bind(
            recipe: RecipeItem,
            context: Context,
            onClickListener: ((RecipeItem) -> Unit)
        ) {
            recipeItemBinding.foodTitle.text = recipe.title + recipe.id

            recipeItemBinding.mainLayout.setOnClickListener {
                onClickListener(recipe)
            }

            Glide.with(context).load(recipe.image).into(recipeItemBinding.foodImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        // Adapter oluşturulduğunda ViewHolder’ı başlatıyor.
        return FavoriteViewHolder(
            LayoutRecipeItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.layout_recipe_item,
                    parent,
                    false
                )
            )
        )
    }

    override fun getItemCount(): Int {
        // Listemizin eleman sayısını veriyor.
        return favoriteRecipes.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        // onCreateViewHolder’dan dönen verilerin bağlama işlemini gerçekleştiriyor.
        holder.bind(favoriteRecipes[position], context, onClickListener!!)
    }
}


