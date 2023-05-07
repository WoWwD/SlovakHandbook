@file:OptIn(ExperimentalMaterial3Api::class)

package com.app.slovakhandbook.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.slovakhandbook.R
import com.app.slovakhandbook.models.CardModel

@Composable
fun ItemCard(cardModel: CardModel, onClick: () -> Unit, isPreview: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        content = {
            Column {
                if (isPreview) {
                    AssetImage(
                        imageName = cardModel.imageName,
                        contentDescription = cardModel.description,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        cardModel.description,
                        modifier = Modifier.padding(12.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.baseline_open_in_full_24),
                        contentDescription = "Open in full",
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    )
}