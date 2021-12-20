package com.oscarvj.aplazotest.ui.view.meals.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oscarvj.aplazotest.data.model.MealModel
import com.oscarvj.aplazotest.databinding.ItemCategoryBinding
import com.oscarvj.aplazotest.listener.ItemListener

class MealsAdapter (
    private val listener: ItemListener,
) : RecyclerView.Adapter<MealsAdapter.ViewHolder>()  {

    private val items: MutableList<MealModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    fun addData(items: List<MealModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

        holder.binding.itemId.setOnClickListener {
            listener.onItemSelected(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MealModel) {

            binding.textViewCategory.text = item.strMeal
            binding.textViewCategory.textSize = 15F

            Glide.with(itemView.context)
                .load(item.strMealThumb)
                .circleCrop()
                .into(binding.image)
        }
    }
}