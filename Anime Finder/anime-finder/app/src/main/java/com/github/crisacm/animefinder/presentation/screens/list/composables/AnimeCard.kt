package com.github.crisacm.animefinder.presentation.screens.list.composables

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Image
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.github.crisacm.animefinder.ui.theme.AnimeFinderTheme
import com.github.crisacm.animefinder.ui.theme.colorYellowRatingStar

@Composable
fun AnimeCard(
    modifier: Modifier,
    coverUrl: String,
    genders: List<String>,
    animeName: String,
    episodes: Int,
    rating: Double
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(start = 24.dp, top = 6.dp, end = 24.dp, bottom = 6.dp)
    ) {
        Row(modifier = modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .width(110.dp)
                    .height(150.dp)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(coverUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    loading = {
                        CircularProgressIndicator()
                    }
                )
                Icon(imageVector = Icons.TwoTone.Image, contentDescription = null)
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp, top = 4.dp, bottom = 4.dp, end = 12.dp)
            ) {
                Text(text = genders.joinToString(separator = " - "), fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = animeName, fontWeight = FontWeight.Bold)
                Row {
                    Text(text = "Episodes ", fontSize = 13.sp)
                    Text(text = episodes.toString(), fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.weight(1f))
                Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.TwoTone.Star, contentDescription = null, tint = colorYellowRatingStar)
                    Text(text = rating.toString(), fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeCardPreview() {
    AnimeFinderTheme {
        AnimeCard(
            modifier = Modifier,
            coverUrl = "",
            genders = listOf("Action", "Adventure", "Fantasy", "Sci-Fi"),
            animeName = "Attack on Titan",
            episodes = 123,
            rating = 4.5
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AnimeCardPreviewDark() {
    AnimeFinderTheme {
        AnimeCard(
            modifier = Modifier,
            coverUrl = "",
            genders = listOf("Action", "Adventure", "Fantasy", "Sci-Fi"),
            animeName = "Attack on Titan",
            episodes = 123,
            rating = 4.5
        )
    }
}
