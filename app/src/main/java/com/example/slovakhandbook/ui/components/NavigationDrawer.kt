@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.slovakhandbook.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.slovakhandbook.R
import com.example.slovakhandbook.ui.screens.MainScreen
import com.example.slovakhandbook.viewmodels.SettingsViewModel
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(navController: NavController, settingsViewModel: SettingsViewModel) {
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
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            Icons.Outlined.Settings,
                            contentDescription = "SettingsApp"
                        )
                    },
                    label = { Text(text = stringResource(R.string.settings)) },
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
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            Icons.Outlined.Info,
                            contentDescription = "AboutApp"
                        )
                    },
                    label = { Text(text = stringResource(R.string.about_app)) },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }.also {
                            navController.navigate("aboutAppScreen")
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
            MainScreen(
                navController = navController,
                onClickNavButton = { scope.launch { drawerState.open() } },
                selectedItem = selectedItem.value,
                settingsViewModel = settingsViewModel
            )
        }
    )
}