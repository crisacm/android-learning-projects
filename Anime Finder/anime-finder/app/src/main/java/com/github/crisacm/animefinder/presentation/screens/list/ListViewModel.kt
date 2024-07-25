package com.github.crisacm.animefinder.presentation.screens.list

import androidx.lifecycle.viewModelScope
import com.github.crisacm.animefinder.AnimeFinderDispatcher
import com.github.crisacm.animefinder.Dispatcher
import com.github.crisacm.animefinder.data.repository.ListRepo
import com.github.crisacm.animefinder.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
  private val listRepo: ListRepo,
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
        Timber.i("Size: ${it.size}")
        setState { copy(isLoading = false, loadData = it) }
      }
    }
  }

  override fun setInitialState(): ListContracts.State =
    ListContracts.State(isLoading = true)

  override fun handleEvent(event: ListContracts.Event) {
    when (event) {
      is ListContracts.Event.Search -> {}
      is ListContracts.Event.Select -> {}
    }
  }
}
