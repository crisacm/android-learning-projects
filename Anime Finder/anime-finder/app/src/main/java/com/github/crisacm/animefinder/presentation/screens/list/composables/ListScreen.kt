package com.github.crisacm.animefinder.presentation.screens.list.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.crisacm.animefinder.ui.theme.AnimeFinderTheme

@Composable
fun ListScreen(modifier: Modifier) {
    val searchBy = remember { mutableStateOf(TextFieldValue("")) }
    val numbers = remember { (0..20).toList() }

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 0.dp, end = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Anime Finder",
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(modifier = Modifier, imageVector = Icons.TwoTone.Settings, contentDescription = null)
                }
            }
            Card(
                shape = RoundedCornerShape(100),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 18.dp, end = 24.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    SearchView(modifier = Modifier, state = searchBy)
                }
            }
            LazyColumn(modifier = Modifier.padding(top = 12.dp)) {
                items(numbers) {
                    AnimeCard(
                        modifier = Modifier,
                        coverUrl = "https://toonamisquad.com/wp-content/uploads/2019/02/Attack-On-Titan-S1.jpg",
                        genders = listOf("Action", "Adventure", "Fantasy", "Sci-Fi"),
                        animeName = "Attack on Titan",
                        extraInfo = listOf("PG13", "2023", "2h 20m"),
                        rating = 4.5
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListPreview() {
    AnimeFinderTheme {
        ListScreen(Modifier)
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ListPreviewDark() {
    AnimeFinderTheme {
        ListScreen(Modifier)
    }
}
