@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.slovakhandbook.ui.components

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.slovakhandbook.models.CardModel
import com.example.slovakhandbook.utils.screenOrientationToLandscape

@Composable
fun MainScreenContent(
    title: String,
    onClickButton: () -> Unit,
    cards: List<CardModel>,
    navController: NavController,
) {
    val context = LocalContext.current
    val activity = context as Activity

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = title,
                onClickNavButton = onClickButton,
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
                        onClick = {
                            navController.navigate(
                                route = "detailScreen" +
                                    "/${cards[it].imageName}" +
                                    "/${cards[it].description}"
                            )
                            screenOrientationToLandscape(activity = activity)
                        }
                    )
                }
            }
        }
    )
}