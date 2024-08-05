package com.github.crisacm.animefinder.data.repository

import com.github.crisacm.animefinder.AnimeFinderDispatcher
import com.github.crisacm.animefinder.Dispatcher
import com.github.crisacm.animefinder.data.api.model.Anime
import com.github.crisacm.animefinder.data.api.service.AnimeClient
import com.github.crisacm.module.easyretrofit.model.onError
import com.github.crisacm.module.easyretrofit.model.onFailed
import com.github.crisacm.module.easyretrofit.model.onSuspendSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class InfoRepoImpl @Inject constructor(
  private val animeClient: AnimeClient,
  @Dispatcher(AnimeFinderDispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) : InfoRepo {

  override fun fetchAnime(
    id: Long,
    onStart: () -> Unit,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
  ): Flow<Anime?> = flow {
    animeClient.fetchAnimeDetail(id.toInt())
      .onSuspendSuccess { emit(data.data) }
      .onFailed { onError(message) }
      .onError { onError("Response error: ${t.message}") }
  }.onStart { onStart() }.onCompletion { onSuccess() }.flowOn(ioDispatcher)
}
