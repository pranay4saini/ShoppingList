package com.pranay.shoppinglist.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.pranay.shoppinglist.R
import com.pranay.shoppinglist.data.db.entities.ShoppingItem
import com.pranay.shoppinglist.databinding.AddShoppingItemBinding

class AddShoppingItem(context: Context, var addDialogListner: AddDialogListner) :AppCompatDialog(context) {
    private lateinit var binding: AddShoppingItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddShoppingItemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.textViewAdd.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val amount = binding.editTextAmount.text.toString()

            if(name.isEmpty() || amount.isEmpty()){
                Toast.makeText(context,"please enter the information",Toast.LENGTH_LONG)
            }
            val item = ShoppingItem(name,amount.toInt())
            addDialogListner.onAddButtonClicked(item)
            dismiss()

        }


        binding.textViewCancel.setOnClickListener {
            cancel()
        }


    }
}