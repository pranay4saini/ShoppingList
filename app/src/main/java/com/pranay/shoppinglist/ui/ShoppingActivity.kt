package com.pranay.shoppinglist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.pranay.shoppinglist.data.db.entities.ShoppingItem

import com.pranay.shoppinglist.databinding.ActivityShoppingBinding

import com.pranay.shoppinglist.other.ShoppingItemAdapter
import com.pranay.shoppinglist.viewModel.ShoppingViewModel
import com.pranay.shoppinglist.viewModel.ShoppingViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class ShoppingActivity : AppCompatActivity(), KodeinAware {


    override val kodein:Kodein by kodein()

    private lateinit var binding: ActivityShoppingBinding
    private val factory: ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val viewModel = ViewModelProviders.of(this,factory).get(ShoppingViewModel::class.java)
        val adapter = ShoppingItemAdapter(listOf(),viewModel)
        binding.recyclerViewShoppingItem.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewShoppingItem.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, {
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