@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.slovakhandbook.ui.screens

import com.example.slovakhandbook.viewmodels.SettingsViewModel
import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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

@Composable
fun MainScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel,
    selectedItem: String,
    onClickNavButton: () -> Unit
) {
    val navDrawerValues = stringArrayResource(id = R.array.nav_drawer_values)
    val cards = getListItems(index = navDrawerValues.indexOf(selectedItem))
    val isShowingPreviewCardState = settingsViewModel.getShowingPreviewCardState()
    val context = LocalContext.current
    val activity = context as Activity

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = selectedItem,
                onClickNavButton = onClickNavButton,
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