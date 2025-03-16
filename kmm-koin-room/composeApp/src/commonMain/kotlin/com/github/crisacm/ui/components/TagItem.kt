@file:OptIn(ExperimentalMaterialApi::class)

package com.github.crisacm.ui.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.crisacm.ui.theme.BlueLight

@Composable
fun TagItem(
  name: String,
  size: Int = 0,
  selectedColor: Color = BlueLight,
  unselectedColor: Color = Color.Gray,
  isSelected: Boolean,
  onSelected: () -> Unit
) {
  Card(
    modifier = Modifier.padding(start = 12.dp),
    shape = RoundedCornerShape(12.dp),
    onClick = { onSelected() },
    elevation = 0.dp,
  ) {
    Row(
      modifier =
        Modifier
          .padding(start = 12.dp, top = 8.dp, bottom = 8.dp, end = 12.dp)
          .height(IntrinsicSize.Min),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = name,
        modifier = Modifier.padding(end = 8.dp),
        color = if (isSelected) selectedColor else unselectedColor,
      )
      Card(
        modifier = Modifier.height(16.dp),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = if (isSelected) selectedColor else unselectedColor
      ) {
        Text(
          modifier =
            Modifier
              .padding(start = 4.dp, end = 4.dp)
              .absoluteOffset(y = (-3.5).dp),
          text = size.toString(),
          textAlign = TextAlign.Center,
          color = Color.White,
          fontSize = 12.sp,
          fontWeight = FontWeight.SemiBold
        )
      }
    }
  }
}
