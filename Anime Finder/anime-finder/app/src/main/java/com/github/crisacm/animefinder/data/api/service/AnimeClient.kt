package com.github.crisacm.animefinder.data.api.service

import com.github.crisacm.animefinder.data.api.model.AnimeResponse
import com.github.crisacm.module.easyretrofit.model.ApiResult
import javax.inject.Inject

class AnimeClient @Inject constructor(
  private val animeService: AnimeService
) {

  suspend fun fetchAnimeList(page: Int): ApiResult<AnimeResponse> =
    animeService.fetchAnimeList(
      page = page,
      limit = PAGING_SIZE
    )

  suspend fun fetchAnimeDetail(id: Int): ApiResult<Any> =
    animeService.fetchAnimeDetail(id)

  companion object {
    private const val PAGING_SIZE = 10
  }
}
