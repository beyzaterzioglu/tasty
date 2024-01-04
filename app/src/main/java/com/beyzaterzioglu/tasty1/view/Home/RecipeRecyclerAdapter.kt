package com.beyzaterzioglu.tasty1.view.Home

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


class RecipeRecyclerAdapter(
    private val context: Context,



) : RecyclerView.Adapter<RecipeRecyclerAdapter.RecipeViewHolder>() {
    private var onClickListener: ((RecipeItem) -> Unit)? = null



    private val diffUtil = object : DiffUtil.ItemCallback<RecipeItem>(){
        override fun areContentsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
            return oldItem == newItem
        }
    }



    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var recipes : List<RecipeItem>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)


    fun setOnItemClickListener(listener : (RecipeItem) -> Unit){
        onClickListener = listener

    }


    class RecipeViewHolder(private var recipeItemBinding: LayoutRecipeItemBinding) : RecyclerView.ViewHolder(recipeItemBinding.root){


        fun bind(recipe : RecipeItem, context : Context, onClickListener : ((RecipeItem) -> Unit)){

            recipeItemBinding.foodTitle.text = recipe.title


            recipeItemBinding.mainLayout.setOnClickListener {
                onClickListener(recipe)
            }

            Glide.with(context).load(recipe.image).into(recipeItemBinding.foodImage)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        //Adapter oluşturulduğunda ViewHolder’ı başlatıyor.
        return RecipeViewHolder(LayoutRecipeItemBinding.bind(LayoutInflater.from(parent.context).inflate(
            R.layout.layout_recipe_item, parent, false)))
    }

    override fun getItemCount(): Int {
        //Listemizin eleman sayısını veriyor.
        return recipes.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
       //onCreateViewHolder’dan dönen verilerin bağlama işlemini gerçekleştiriyor.
        holder.bind(recipes[position], context, onClickListener!!)


    }
}
