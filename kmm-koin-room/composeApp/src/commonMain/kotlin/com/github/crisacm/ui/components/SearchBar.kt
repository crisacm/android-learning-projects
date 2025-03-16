package com.github.crisacm.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.crisacm.ui.theme.BlueLightBorder
import com.github.crisacm.ui.theme.LightGray

@Composable
fun SearchBar(
  modifier: Modifier = Modifier,
  searchBy: String,
  onSearch: (String) -> Unit
) {
  Box(modifier = modifier) {
    OutlinedTextField(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(50.dp),
      value = searchBy,
      onValueChange = { onSearch(it) },
      leadingIcon = {
        Icon(
          Icons.Filled.Search,
          contentDescription = "Search tasks by...",
          modifier = Modifier.padding(start = 8.dp),
        )
      },
      trailingIcon = {
        if (searchBy.isNotEmpty()) {
          IconButton(
            modifier = Modifier.padding(end = 8.dp),
            onClick = { onSearch("") }) {
            Icon(Icons.Filled.Clear, contentDescription = "Clear")
          }
        }
      },
      placeholder = { Text(text = "Search...") },
      singleLine = true,
      colors = TextFieldDefaults.outlinedTextFieldColors(
        backgroundColor = LightGray,
        focusedBorderColor = BlueLightBorder,
        unfocusedBorderColor = Color.LightGray,
      )
    )
  }
}