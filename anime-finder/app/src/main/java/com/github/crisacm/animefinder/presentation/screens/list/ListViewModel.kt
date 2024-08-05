package com.github.crisacm.animefinder.presentation.screens.list

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.crisacm.animefinder.AnimeFinderDispatcher
import com.github.crisacm.animefinder.Dispatcher
import com.github.crisacm.animefinder.data.api.model.Anime
import com.github.crisacm.animefinder.data.api.service.AnimeClient
import com.github.crisacm.animefinder.data.api.service.AnimeService
import com.github.crisacm.animefinder.data.api.utils.AnimeSource
import com.github.crisacm.animefinder.data.repository.ListRepo
import com.github.crisacm.animefinder.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
  private val listRepo: ListRepo,
  private val animeService: AnimeService,
  @Dispatcher(AnimeFinderDispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel<ListContracts.Event, ListContracts.State, ListContracts.Effect>() {

  init {
    viewModelScope.launch(ioDispatcher) {
      listRepo.fetchAnimeList(
        page = 1,
        onStart = { setState { copy(isLoading = true) } },
        onSuccess = { setState { copy(isLoading = false) } },
        onError = { setState { copy(isLoading = false, error = it) } }
      ).collect {
        setState { copy(isLoading = false, loadData = it) }
      }
    }
  }

  val animes: Flow<PagingData<Anime>> = Pager(PagingConfig(pageSize = 6)) {
    AnimeSource(animeService)
  }.flow.cachedIn(viewModelScope)

  override fun setInitialState(): ListContracts.State =
    ListContracts.State(isLoading = true)

  override fun handleEvent(event: ListContracts.Event) {
    when (event) {
      is ListContracts.Event.Search -> {}
      is ListContracts.Event.Select -> { setEffect { ListContracts.Effect.Navigation.ToDetails(event.id) } }
    }
  }
}
