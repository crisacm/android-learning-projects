package com.github.crisacm.animefinder.presentation.screens.list.composables

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Image
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
  extraInfo: List<String>,
  rating: Double,
  onClick: () -> Unit
) {
  Column(
    modifier = modifier.padding(start = 24.dp, top = 6.dp, end = 24.dp, bottom = 6.dp)
  ) {
    Card(
      shape = RoundedCornerShape(8.dp),
      elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
      onClick = onClick
    ) {
      Box(
        modifier = Modifier
          .width(150.dp)
          .height(200.dp)
          .background(Color.Gray),
        contentAlignment = Alignment.Center
      ) {
        SubcomposeAsyncImage(
          model = ImageRequest.Builder(LocalContext.current)
            .data(coverUrl)
            .crossfade(true)
            .build(),
          modifier = Modifier.fillMaxSize(),
          contentDescription = null,
          contentScale = ContentScale.FillWidth,
          loading = {
            CircularProgressIndicator()
          }
        )
      }
    }
    Text(
      text = animeName,
      modifier = Modifier
        .padding(start = 4.dp, top = 6.dp, end = 4.dp)
        .width(150.dp),
      overflow = TextOverflow.Ellipsis,
      lineHeight = 16.sp,
      maxLines = 2
    )
    Box(modifier = Modifier
      .width(150.dp)
      .padding(top = 6.dp)) {
      Column {
        LazyRow {
          items(5) { i ->
            Icon(
              imageVector = Icons.TwoTone.Star,
              contentDescription = null,
              tint = if ((i + 1) <= (rating.toInt() / 2)) colorYellowRatingStar else Color.Gray
            )
          }
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
      extraInfo = listOf("PG13", "2023", "2h 20m"),
      rating = 4.5,
      onClick = {}
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
      extraInfo = listOf("PG13", "2023", "2h 20m"),
      rating = 4.5,
      onClick = {}
    )
  }
}
