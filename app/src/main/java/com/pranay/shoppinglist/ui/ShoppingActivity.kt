package com.pranay.shoppinglist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.pranay.shoppinglist.R
import com.pranay.shoppinglist.data.db.ShoppingDatabase
import com.pranay.shoppinglist.data.repositories.ShoppingRepository
import com.pranay.shoppinglist.viewModel.ShoppingViewModel
import com.pranay.shoppinglist.viewModel.ShoppingViewModelFactory

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)


        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this,factory).get(ShoppingViewModel::class.java)

    }
}