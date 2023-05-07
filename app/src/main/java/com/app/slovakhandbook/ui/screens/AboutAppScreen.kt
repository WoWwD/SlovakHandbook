@file:OptIn(ExperimentalMaterial3Api::class)

package com.app.slovakhandbook.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.slovakhandbook.R
import com.app.slovakhandbook.ui.components.MyTopAppBar

@Composable
fun AboutAppScreen(navController: NavController) {
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = stringResource(R.string.about_app),
                onClickNavButton = { navController.popBackStack() },
                imageVector = Icons.Outlined.ArrowBack
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Text(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.info_app_text)
            )
        }
    }
}