package com.example.editablelist.domain.repository

import com.example.editablelist.domain.model.Item

interface ItemsRepository {
    fun getListItems(): List<Item>
    fun putItemIntoList(item: Item)
    fun deleteItemFromList(item: Item)
}