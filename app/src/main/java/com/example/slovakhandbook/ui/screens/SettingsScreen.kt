@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.slovakhandbook.ui.screens

import com.example.slovakhandbook.viewmodels.SettingsViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.slovakhandbook.R
import com.example.slovakhandbook.ui.components.MyTopAppBar

@Composable
fun SettingsScreen(navController: NavController, settingsViewModel: SettingsViewModel) {
    val showingPreviewCardState = remember { mutableStateOf(settingsViewModel.getShowingPreviewCardState()) }

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "Настройки",
                onClickNavButton = { navController.popBackStack() },
                imageVector = Icons.Outlined.ArrowBack
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Switch(
                    checked = showingPreviewCardState.value,
                    onCheckedChange = {
                        showingPreviewCardState.value = it
                        settingsViewModel.setShowingPreviewCardState(it)
                    }
                )
                Spacer(modifier = Modifier.width(24.dp))
                Text(text = stringResource(R.string.get_preview_on_card))
            }
        }
    }
}