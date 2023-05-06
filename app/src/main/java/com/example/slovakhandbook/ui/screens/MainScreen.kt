@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.slovakhandbook.ui.screens

import com.example.slovakhandbook.viewmodels.SettingsViewModel
import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.slovakhandbook.R
import com.example.slovakhandbook.models.CardModel
import com.example.slovakhandbook.ui.components.ItemCard
import com.example.slovakhandbook.ui.components.MyTopAppBar
import com.example.slovakhandbook.utils.IdsList
import com.example.slovakhandbook.utils.screenOrientationToLandscape
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController, settingsViewModel: SettingsViewModel) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navDrawerValues = stringArrayResource(id = R.array.nav_drawer_values)
    val selectedItem = rememberSaveable { mutableStateOf(navDrawerValues[0]) }
    val isShowingPreviewCardState = settingsViewModel.getShowingPreviewCardState()
    val context = LocalContext.current
    val activity = context as Activity


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
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            Icons.Outlined.Settings,
                            contentDescription = "SettingsApp"
                        )
                    },
                    label = { Text(text = "Настройки") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }.also {
                            navController.navigate("settingsScreen")
                        }
                    },
                    modifier = Modifier.padding(
                        vertical = 4.dp,
                        horizontal = 12.dp
                    )
                )
            }
        },
        content = {
            val cards = getListItems(index = navDrawerValues.indexOf(selectedItem.value))

            Scaffold(
                topBar = {
                    MyTopAppBar(
                        title = selectedItem.value,
                        onClickNavButton = { scope.launch { drawerState.open() } },
                        imageVector = Icons.Outlined.Menu
                    )
                },
                content = { padding ->
                    LazyColumn(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                        contentPadding = padding
                    ) { items(cards.size)
                        {
                            ItemCard(
                                cardModel = cards[it],
                                isPreview = isShowingPreviewCardState,
                                onClick = {
                                    navController.navigate(
                                        route = "detailScreen" +
                                            "/${cards[it].imageName}" +
                                            "/${cards[it].description}"
                                    ).also {
                                        screenOrientationToLandscape(activity = activity)
                                    }
                                }
                            )
                        }
                    }
                }
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