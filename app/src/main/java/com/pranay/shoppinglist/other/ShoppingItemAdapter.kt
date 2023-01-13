package com.pranay.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.pranay.shoppinglist.R
import com.pranay.shoppinglist.data.db.entities.ShoppingItem
import com.pranay.shoppinglist.databinding.ShoppingItemBinding
import com.pranay.shoppinglist.viewModel.ShoppingViewModel
import com.pranay.shoppinglist.viewModel.ShoppingViewModelFactory

class ShoppingItemAdapter(
    var items:List<ShoppingItem>,
    private val viewModel: ShoppingViewModel

    ):RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val binding = ShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent ,false)


        return ShoppingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem = items[position]

        holder.binding.textViewName.text = currentShoppingItem.name
        holder.binding.textViewAmount.text = "${currentShoppingItem.amount}"
        holder.binding.ivDelete.setOnClickListener{
            viewModel.delete(currentShoppingItem)
        }

        holder.binding.ivPlus.setOnClickListener {
            currentShoppingItem.amount++
            viewModel.upsert(currentShoppingItem)
        }
        holder.binding.ivMinus.setOnClickListener {
            if(currentShoppingItem.amount > 0 ){
                currentShoppingItem.amount--
                viewModel.upsert(currentShoppingItem)
            }
        }
    }

    override fun getItemCount(): Int {

        return items.size
    }

    inner class ShoppingViewHolder(val binding: ShoppingItemBinding): RecyclerView.ViewHolder(binding.root)
}