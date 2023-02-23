package com.example.editablelist.domain.repository

import android.util.Log
import com.example.editablelist.domain.model.Item
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor() :ItemsRepository {

    private val listItems= mutableListOf(
        Item(scrambled = "fff", guess = "ggg", isRight = "-"),
        Item(scrambled = "ttt", guess = "ggg", isRight = "-"),
        Item(scrambled = "hhh", "yyyy", isRight = "-"),
        Item(scrambled = "kkk", guess = "kkk", isRight = "+")
    )

    override fun getListItems(): List<Item> {
        return listItems
    }

    override fun putItemIntoList(item: Item) {
        listItems.add(item)
    }

    override fun deleteItemFromList(item: Item) {
        Log.d("itemindex", "item $item")
        Log.d("itemindex", "items list $listItems")
        val index = listItems.indexOf(item)
        Log.d("itemindex", "index $index")
        listItems.removeAt(index)
        Log.d("itemindex", "items list $listItems")
        //if (listItems.contains(item)) listItems.remove(item)

    }
}