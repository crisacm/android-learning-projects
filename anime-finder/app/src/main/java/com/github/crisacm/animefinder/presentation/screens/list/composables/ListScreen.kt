package com.github.crisacm.animefinder.presentation.screens.list.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.SettingsInputComponent
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.github.crisacm.animefinder.data.api.model.Anime
import com.github.crisacm.animefinder.presentation.base.SIDE_EFFECTS_KEY
import com.github.crisacm.animefinder.presentation.screens.list.ListContracts
import com.github.crisacm.animefinder.ui.theme.AnimeFinderTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach

@Composable
fun ListScreen(
  animes: Flow<PagingData<Anime>>,
  state: ListContracts.State,
  effectFlow: Flow<ListContracts.Effect>?,
  onEventSent: (ListContracts.Event) -> Unit,
  onNavigationRequested: (ListContracts.Effect.Navigation) -> Unit
) {
  val searchBy = remember { mutableStateOf(TextFieldValue("")) }
  val list = animes.collectAsLazyPagingItems()

  LaunchedEffect(SIDE_EFFECTS_KEY) {
    effectFlow?.onEach { effect ->
      when (effect) {
        is ListContracts.Effect.Navigation.ToDetails -> {
          onNavigationRequested(effect)
        }
      }
    }?.collect()
  }

  Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
    Column(modifier = Modifier.padding(innerPadding)) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(start = 24.dp, top = 6.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(text = "Anime Finder", fontWeight = FontWeight.Bold, fontSize = 28.sp)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /*TODO*/ }) {
          Icon(modifier = Modifier, imageVector = Icons.TwoTone.SettingsInputComponent, contentDescription = null)
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
      when {
        state.isLoading -> {
          Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
          }
        }

        !state.error.isNullOrEmpty() -> {
          ListAlert(modifier = Modifier.fillMaxSize(), typeAlert = TypeAlert.ERROR_FETCHING_DATA)
        }

        state.error.isNullOrEmpty() && state.loadData.isEmpty() -> {
          ListAlert(modifier = Modifier.fillMaxSize(), typeAlert = TypeAlert.EMPTY)
          Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "No data", fontWeight = FontWeight.Bold, fontSize = 24.sp)
          }
        }

        state.loadData.isNotEmpty() -> {
          /*
          LazyVerticalGrid(
            modifier = Modifier.padding(top = 12.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            items(state.loadData) { anime ->
              AnimeCard(
                modifier = Modifier,
                coverUrl = anime.images.jpg?.imageUrl ?: "",
                genders = anime.genres?.map { it.name ?: "" } ?: emptyList(),
                animeName = anime.title,
                extraInfo = listOf(anime.duration, anime.rating),
                rating = anime.score,
                onClick = {
                  onEventSent(ListContracts.Event.Select(id = anime.malId.toLong()))
                }
              )
            }
          }
          */

          LazyVerticalGrid(
            modifier = Modifier.padding(top = 12.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            items(
              count = list.itemCount,
              key = list.itemKey { it.malId }
            ) { index ->
              val anime = list[index]
              if (anime != null) {
                AnimeCard(
                  modifier = Modifier,
                  coverUrl = anime.images.jpg?.imageUrl ?: "",
                  genders = anime.genres?.map { it.name ?: "" } ?: emptyList(),
                  animeName = anime.title,
                  extraInfo = listOf(anime.duration, anime.rating),
                  rating = anime.score,
                  onClick = {
                    onEventSent(ListContracts.Event.Select(id = anime.malId.toLong()))
                  }
                )
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
fun ListPreview() {
  AnimeFinderTheme {
    ListScreen(
      animes = remember { flowOf(PagingData.empty()) },
      state = ListContracts.State(),
      effectFlow = null,
      onEventSent = {},
      onNavigationRequested = {}
    )
  }
}
