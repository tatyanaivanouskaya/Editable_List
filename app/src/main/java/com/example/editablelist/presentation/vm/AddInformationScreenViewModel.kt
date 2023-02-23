package com.example.editablelist.presentation.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.editablelist.domain.model.Item
import com.example.editablelist.domain.repository.ItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddInformationScreenViewModel @Inject constructor(
    private val listItemsRepository: ItemsRepository
) : ViewModel() {

    var listItems = mutableStateOf(value = listOf<Item>())

    init {
        getList()
    }

    fun getList(){
        listItems.value = listItemsRepository.getListItems()
    }

    fun addItem(item: Item){
        listItemsRepository.putItemIntoList(item = item)
        getList()
    }

    fun deleteItem(item: Item){
        listItemsRepository.deleteItemFromList(item = item)
        getList()
    }


}