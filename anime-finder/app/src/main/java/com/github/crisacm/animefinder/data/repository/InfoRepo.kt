package com.github.crisacm.animefinder.data.repository

import androidx.annotation.WorkerThread
import com.github.crisacm.animefinder.data.api.model.Anime
import kotlinx.coroutines.flow.Flow

interface InfoRepo {

  @WorkerThread
  fun fetchAnime(
    id: Long,
    onStart: () -> Unit = {},
    onSuccess: () -> Unit = {},
    onError: (String) -> Unit = {}
  ): Flow<Anime?>
}
