package com.example.editablelist.presentation

import com.example.editablelist.domain.model.Item
import com.example.editablelist.domain.repository.ItemsRepository
import com.example.editablelist.presentation.vm.AddInformationScreenViewModel
import org.junit.Assert
import org.junit.Test


internal class AddInformationScreenViewModelTest {

    val viewModel = AddInformationScreenViewModel(
        object : ItemsRepository {
            private val listItems = mutableListOf(
                Item(scrambled = "fff", guess = "ggg", isRight = "-"),
                Item(scrambled = "ttt", guess = "ggg", isRight = "-")
            )


            override fun getListItems(): List<Item> {
                return listItems
            }

            override fun putItemIntoList(item: Item) {
                listItems.add(item)
            }

            override fun deleteItemFromList(item: Item) {
                listItems.remove(item)
            }
        }
    )

    @Test
    fun `returns list of 2 items`() {
        val result = viewModel.listItems.value.size

        Assert.assertEquals(2, result)


    }

    @Test
    fun `returns list of 3 items`() {
        viewModel.addItem(Item("rrrrr", "yyyyy", "-"))
        val result = viewModel.listItems.value.size

        Assert.assertEquals(3, result)
    }

    @Test
    fun `returns empty list`() {
        repeat(2) {
            viewModel.deleteItem(viewModel.listItems.value[0])
        }
        val result = viewModel.listItems.value.size

        Assert.assertEquals(0, result)
    }


}