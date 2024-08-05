package com.github.crisacm.animefinder.presentation.screens.info.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.github.crisacm.animefinder.presentation.base.SIDE_EFFECTS_KEY
import com.github.crisacm.animefinder.presentation.screens.info.InfoContracts
import com.github.crisacm.animefinder.ui.theme.AnimeFinderTheme
import com.github.crisacm.animefinder.ui.theme.colorYellowRatingStar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(
  state: InfoContracts.State,
  effectFlow: Flow<InfoContracts.Effect>?,
  onEventSent: (InfoContracts.Event) -> Unit,
  onNavigationRequested: (InfoContracts.Effect.Navigation) -> Unit,
) {
  val cover = "https://toonamisquad.com/wp-content/uploads/2019/02/Attack-On-Titan-S1.jpg"
  val genders = listOf("Action", "Adventure", "Fantasy", "Mystery", "Sci-Fi")
  val title = "Attack on Titan"
  val extraInfo = listOf("PG13", "2023", "2h 20m")
  val description = "No cabe duda de que Shingeki no Kyojin es uno de los manganimes más aclamados " +
          "de los últimos años. Hajime Isayama publicó el primer tomo de su creación un 9 de septiembre " +
          "de 2009 y lanzó el último el pasado 9 de abril de 2021. En cuanto al anime, empezó a emitirse " +
          "en abril de 2013 y finalizará este 2023 con las Partes 1 y 2 de la Parte 3 de su Temporada 4 " +
          "(sí, sí, sabemos que parece una frase escrita por los hermanos Marx). Aunque todavía desconocemos " +
          "si el desenlace definitivo de la historia divergirá en el anime polémico final del manga, por el " +
          "que Isayama se disculpó entre lágrimas, no tardaremos en salir de dudas. Al fin y al cabo, ya hay " +
          "fecha de estreno y plataforma para ver el desenlace de la serie."
  val rating = 8.5

  LaunchedEffect(SIDE_EFFECTS_KEY) {
    effectFlow?.onEach {
      when (it) {
        is InfoContracts.Effect.Navigation.onBack -> {
          onNavigationRequested(it)
        }
      }
    }?.collect()
  }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      CenterAlignedTopAppBar(
        title = {
          Text(
            text = "Anime Details",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        },
        navigationIcon = {
          IconButton(onClick = { onEventSent(InfoContracts.Event.onBack) }) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
          }
        }
      )
    }
  ) { innerPadding ->
    when {
      state.isLoading -> {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
          CircularProgressIndicator()
        }
      }

      !state.isLoading && state.data != null -> {
        Column(
          modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
        ) {
          Card(
            modifier = Modifier
              .fillMaxWidth()
              .height(300.dp)
              .padding(12.dp)
          ) {
            SubcomposeAsyncImage(
              modifier = Modifier.fillMaxSize(),
              model = ImageRequest.Builder(LocalContext.current)
                .data(state.data.images.jpg?.imageUrl)
                .crossfade(true)
                .build(),
              contentDescription = null,
              contentScale = ContentScale.Crop,
              loading = {
                CircularProgressIndicator()
              }
            )
          }
          Text(
            text = state.data.genres?.map { it.name }?.joinToString(separator = " - ") ?: "",
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(start = 24.dp)
          )
          Text(
            text = state.data.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 24.dp)
          )
          LazyRow(modifier = Modifier.padding(start = 24.dp, top = 8.dp)) {
            items(extraInfo) { text ->
              Card(
                shape = RoundedCornerShape(60),
                modifier = Modifier.padding(end = 8.dp),
                border = BorderStroke(1.dp, Color.Gray),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
              ) {
                Text(text = text, modifier = Modifier.padding(start = 12.dp, end = 12.dp))
              }
            }
          }
          Text(
            text = state.data.synopsis,
            fontSize = 13.sp,
            modifier = Modifier.padding(start = 24.dp, top = 16.dp, end = 24.dp)
          )
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .padding(start = 24.dp, top = 24.dp, end = 24.dp),
            contentAlignment = Alignment.Center
          ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text(text = "Rating")
              Text(text = state.data.score.toString(), fontSize = 26.sp, fontWeight = FontWeight.Bold)
              LazyRow {
                items(5) { i ->
                  Icon(
                    imageVector = Icons.TwoTone.Star,
                    contentDescription = null,
                    tint = if ((i + 1) <= (state.data.score.toInt() / 2)) colorYellowRatingStar else Color.Gray
                  )
                }
              }
            }
          }
        }
      }
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InfoScreenPreview() {
  AnimeFinderTheme {
    InfoScreen(
      state = InfoContracts.State(),
      effectFlow = null,
      onEventSent = {},
      onNavigationRequested = {}
    )
  }
}
