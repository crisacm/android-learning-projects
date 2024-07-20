package com.github.crisacm.animefinder.presentation.screens.list.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnimeCard() {
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = 6.dp, end = 24.dp, bottom = 6.dp)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .width(110.dp)
                    .height(150.dp)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.TwoTone.Star, contentDescription = null)
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                Text(text = "Genders * separeted * with * dots", fontSize = 12.sp)
                Text(text = "Anime Name", fontWeight = FontWeight.Bold)
                Text(text = "Episodes", fontSize = 13.sp)
                Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                    Text(text = "0.0", fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}
