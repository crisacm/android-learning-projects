package com.github.crisacm.animefinder.data.repository

import androidx.annotation.WorkerThread
import com.github.crisacm.animefinder.data.api.model.Anime
import kotlinx.coroutines.flow.Flow

interface ListRepo {

  @WorkerThread
  fun fetchAnimeList(
    page: Int,
    onStart: () -> Unit = {},
    onSuccess: () -> Unit = {},
    onError: (String) -> Unit = {}
  ): Flow<List<Anime>>
}
