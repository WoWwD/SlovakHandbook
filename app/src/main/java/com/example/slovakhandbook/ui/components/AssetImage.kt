package com.example.slovakhandbook.ui.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext

@Composable
fun AssetImage(
    imageName: String,
    contentDescription: String,
    modifier: Modifier
) {
    val context = LocalContext.current
    val assetManger = context.assets
    val inputStream = assetManger.open(imageName)
    val bitMap = BitmapFactory.decodeStream(inputStream)

    Image(
        bitmap = bitMap.asImageBitmap(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}