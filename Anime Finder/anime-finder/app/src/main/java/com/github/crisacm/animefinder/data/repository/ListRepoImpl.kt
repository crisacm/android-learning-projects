package com.github.crisacm.animefinder.data.repository

import androidx.annotation.WorkerThread
import com.github.crisacm.animefinder.AnimeFinderDispatcher
import com.github.crisacm.animefinder.Dispatcher
import com.github.crisacm.animefinder.data.api.model.Anime
import com.github.crisacm.animefinder.data.api.service.AnimeClient
import com.github.crisacm.module.easyretrofit.model.onError
import com.github.crisacm.module.easyretrofit.model.onFailed
import com.github.crisacm.module.easyretrofit.model.onSuccess
import com.github.crisacm.module.easyretrofit.model.onSuspendSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class ListRepoImpl @Inject constructor(
  private val animeClient: AnimeClient,
  @Dispatcher(AnimeFinderDispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) : ListRepo {

  @WorkerThread
  override fun fetchAnimeList(
    page: Int,
    onStart: () -> Unit,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
  ): Flow<List<Anime>> = flow {
    val response = animeClient.fetchAnimeList(page)
    response.onSuspendSuccess { emit(data.data) }
      .onFailed { onError(message) }
      .onError { onError("Response error: ${t.message}") }
  }
    .onStart { onStart() }
    .onCompletion { onSuccess() }
    .flowOn(ioDispatcher)
}