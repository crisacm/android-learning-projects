package com.github.crisacm.animefinder.presentation.screens.list.composables

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.crisacm.animefinder.ui.theme.AnimeFinderTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    modifier: Modifier,
    state: MutableState<TextFieldValue>
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = state.value,
        onValueChange = { state.value = it },
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(onClick = { state.value = TextFieldValue("") }) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        },
        placeholder = {
            Text(text = "Search here...", modifier = Modifier.padding(PaddingValues(start = 12.dp)))
        },
        singleLine = true,
        shape = RectangleShape,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.textFieldColors(
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchViewPreviewDark() {
    val state = remember { mutableStateOf(TextFieldValue("")) }
    AnimeFinderTheme {
        SearchView(modifier = Modifier, state = state)
    }
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val state = remember { mutableStateOf(TextFieldValue("")) }
    AnimeFinderTheme {
        SearchView(modifier = Modifier, state = state)
    }
}
