package com.pranay.shoppinglist.ui

import com.pranay.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListner {
    fun onAddButtonClicked(item:ShoppingItem)
}