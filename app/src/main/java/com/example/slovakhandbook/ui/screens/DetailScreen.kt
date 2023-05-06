@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.slovakhandbook.ui.screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.slovakhandbook.ui.components.AssetImage
import com.example.slovakhandbook.ui.components.MyTopAppBar
import com.example.slovakhandbook.utils.screenOrientationToDefault

@Composable
fun DetailScreen(
    imageName: String,
    description: String,
    navController: NavController
) {
    val context = LocalContext.current
    val activity = context as Activity

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = description,
                onClickNavButton = {
                    navController.popBackStack().also {
                        screenOrientationToDefault(activity = activity)
                    }
                },
                imageVector = Icons.Outlined.ArrowBack
            )
        },
    ) { padding -> ImageZoom(imageName, description, padding) }
}

@Composable
fun ImageZoom(imageName: String, description: String, padding: PaddingValues) {
    val scale = remember { mutableStateOf(1f) }

    Box(
        modifier = Modifier
            .clip(RectangleShape)
            .fillMaxSize()
            .padding(padding)
            .background(Color.White)
            .pointerInput(Unit) {
                detectTransformGestures { _, _, zoom, _ ->
                    scale.value *= zoom
                }
            }
    ) {
        AssetImage(
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer(
                    scaleX = maxOf(1f, minOf(3f, scale.value)),
                    scaleY = maxOf(1f, minOf(3f, scale.value)),
                ),
            imageName = imageName,
            contentDescription = description,
        )
    }
}