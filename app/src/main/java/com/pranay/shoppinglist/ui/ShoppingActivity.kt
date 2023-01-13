package com.pranay.shoppinglist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.pranay.shoppinglist.R
import com.pranay.shoppinglist.data.db.ShoppingDatabase
import com.pranay.shoppinglist.data.db.entities.ShoppingItem
import com.pranay.shoppinglist.data.repositories.ShoppingRepository
import com.pranay.shoppinglist.databinding.ActivityShoppingBinding

import com.pranay.shoppinglist.other.ShoppingItemAdapter
import com.pranay.shoppinglist.viewModel.ShoppingViewModel
import com.pranay.shoppinglist.viewModel.ShoppingViewModelFactory

class ShoppingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this,factory).get(ShoppingViewModel::class.java)
        val adapter = ShoppingItemAdapter(listOf(),viewModel)
        binding.recyclerViewShoppingItem.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewShoppingItem.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        binding.fab.setOnClickListener {
            AddShoppingItem(this,
            object : AddDialogListner{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }

    }
}