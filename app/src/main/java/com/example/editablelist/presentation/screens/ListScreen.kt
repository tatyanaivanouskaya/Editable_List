package com.example.editablelist.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.editablelist.domain.model.Item
import com.example.editablelist.presentation.vm.AddInformationScreenViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddInformationScreen(
    vm: AddInformationScreenViewModel = hiltViewModel()
) {
    var scrambled by remember {
        mutableStateOf("")
    }
    var guess by remember {
        mutableStateOf("")
    }
    var isRight by remember {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add information", fontSize = 18.sp)
        OutlinedTextField(
            value = scrambled,
            onValueChange = {
                scrambled = it

            },
            modifier = Modifier
                .padding(top = 8.dp, bottom = 4.dp)
                .fillMaxWidth(),
            label = {
                Text(text = "Enter your word")
            }
        )
        OutlinedTextField(
            value = guess,
            onValueChange = {
                guess = it
            },
            modifier = Modifier
                .padding(top = 4.dp, bottom = 4.dp)
                .fillMaxWidth(),
            label = {
                Text(text = "Enter your word")
            }
        )
        OutlinedTextField(
            value = isRight,
            onValueChange = {
                isRight = it
            },
            modifier = Modifier
                .padding(top = 4.dp, bottom = 8.dp)
                .fillMaxWidth(),
            label = {
                Text(text = "Enter your word")
            }
        )
        Button(
            onClick = {
                vm.addItem(
                    item = Item(
                        scrambled = scrambled,
                        guess = guess,
                        isRight = isRight
                    )
                )
                scrambled = ""
                guess = ""
                isRight = ""
                keyboardController?.hide()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add")
        }

        ListScreen(vm = vm)
    }

}

@Composable
fun ListScreen(vm: AddInformationScreenViewModel) {

    val openDialog = remember { mutableStateOf(false) }
    val list by remember {
        mutableStateOf(vm.listItems.value)
    }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "#", modifier = Modifier.weight(1F))
            Text(text = "scrambled", modifier = Modifier.weight(3F))
            Text(text = "guess", modifier = Modifier.weight(3F))
            Text(text = "Is right?", modifier = Modifier.weight(3F))
        }
        Divider()

        LazyColumn(modifier = Modifier.align(CenterHorizontally),
            content = {
                itemsIndexed(list) { index, item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 8.dp)
                            .clickable {
                                openDialog.value = true
                            },

                        ) {
                        Text(text = (index + 1).toString(), modifier = Modifier.weight(1F))
                        Text(text = item.scrambled, modifier = Modifier.weight(3F))
                        Text(text = item.guess, modifier = Modifier.weight(3F))
                        Text(text = item.isRight, modifier = Modifier.weight(3F))

                        if (openDialog.value) {
                            AlertDialog(
                                onDismissRequest = {
                                    openDialog.value = false
                                },
                                title = { Text(text = "Подтверждение действия") },
                                text = { Text("Вы действительно хотите удалить выбранный элемент?") },
                                buttons = {
                                    Row(
                                        modifier = Modifier.padding(all = 8.dp),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Button(
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(end = 8.dp),
                                            onClick = {
                                                vm.deleteItem(item = vm.listItems.value[index])
                                                openDialog.value = false
                                            }
                                        ) {
                                            Text("Удалить")
                                        }
                                        Button(
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(start = 8.dp),
                                            onClick = { openDialog.value = false }
                                        ) {
                                            Text("Отмена")
                                        }
                                    }
                                }
                            )
                        }
                    }
                    Divider()


                }

            })
    }
}

@Preview(showBackground = true)
@Composable
fun AddInformationScreenPreview() {
    AddInformationScreen()
}