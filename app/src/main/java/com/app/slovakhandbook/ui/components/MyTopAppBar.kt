@file:OptIn(ExperimentalMaterial3Api::class)

package com.app.slovakhandbook.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun MyTopAppBar(title: String, onClickNavButton: ()-> Unit, imageVector: ImageVector) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.smallTopAppBarColors(),
        navigationIcon = {
            IconButton(
                onClick = onClickNavButton,
                content = {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = "IconButton"
                    )
                }
            )
        }
    )
}