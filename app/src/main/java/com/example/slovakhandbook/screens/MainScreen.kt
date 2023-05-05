@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.slovakhandbook.screens

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.slovakhandbook.R
import com.example.slovakhandbook.models.CardModel
import com.example.slovakhandbook.ui.components.MainScreenContent
import com.example.slovakhandbook.utils.IdsList
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navDrawerValues = stringArrayResource(id = R.array.nav_drawer_values)
    val selectedItem = rememberSaveable { mutableStateOf(navDrawerValues[0]) }


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                windowInsets = WindowInsets(top = 24.dp, bottom = 24.dp)
            ) {
                navDrawerValues.forEach { item ->
                    NavigationDrawerItem(
                        label = { Text(item) },
                        selected = item == selectedItem.value,
                        onClick = {
                            selectedItem.value = item
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(
                            vertical = 4.dp,
                            horizontal = 12.dp
                        )
                    )
                }
                Divider(
                    Modifier.padding(
                        vertical = 8.dp,
                        horizontal = 12.dp
                    )
                )
//                NavigationDrawerItem(
//                    label = {
//                        Row(
//                            horizontalArrangement = Arrangement.SpaceBetween
//                        ) {
//                            Icon(
//                                Icons.Outlined.Info,
//                                contentDescription = "InfoApp"
//                            )
//                            Spacer(modifier = Modifier.width(16.dp))
//                            Text(text = "О приложении")
//                        }
//                    },
//                    selected = false,
//                    onClick = { scope.launch { drawerState.close() } },
//                    modifier = Modifier.padding(
//                        vertical = 4.dp,
//                        horizontal = 12.dp
//                    )
//                )
            }
        },
        content = {
            MainScreenContent(
                title = selectedItem.value,
                onClickButton = { scope.launch { drawerState.open() } },
                cards = getListItems(index = navDrawerValues.indexOf(selectedItem.value)),
                navController = navController,
            )
        }
    )
}

@Composable
private fun getListItems(index: Int): List<CardModel> {
    val list = ArrayList<CardModel>()
    val array = stringArrayResource(id = IdsList.listIdsCardsNavDriver[index])
    array.forEach { item ->
        val res = item.split("|")
        list.add(CardModel(res[1], res[0]))
    }
    return list
}