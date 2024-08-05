package com.github.crisacm.animefinder.presentation.screens.info

import androidx.lifecycle.viewModelScope
import com.github.crisacm.animefinder.AnimeFinderDispatcher
import com.github.crisacm.animefinder.Dispatcher
import com.github.crisacm.animefinder.data.repository.InfoRepo
import com.github.crisacm.animefinder.presentation.base.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = InfoViewModel.InfoViewModelFactory::class)
class InfoViewModel @AssistedInject constructor(
  @Assisted private val animeId: Long,
  private val infoRepo: InfoRepo,
  @Dispatcher(AnimeFinderDispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel<InfoContracts.Event, InfoContracts.State, InfoContracts.Effect>() {

  @dagger.assisted.AssistedFactory
  interface InfoViewModelFactory {
    fun create(animeId: Long): InfoViewModel
  }

  init {
    viewModelScope.launch(ioDispatcher) {
      infoRepo.fetchAnime(
        id = animeId,
        onStart = { setState { copy(isLoading = true) } },
        onSuccess = { setState { copy(isLoading = false) } },
        onError = { setState { copy(isLoading = false, error = it) } }
      ).collectLatest {
        setState { copy(isLoading = false, error = null, data = it) }
      }
    }
  }

  override fun setInitialState(): InfoContracts.State = InfoContracts.State(isLoading = true)

  override fun handleEvent(event: InfoContracts.Event) {
    when (event) {
      is InfoContracts.Event.onBack -> {
        setEffect { InfoContracts.Effect.Navigation.onBack }
      }
    }
  }
}
