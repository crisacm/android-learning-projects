package com.github.crisacm.animefinder.data.api.service

import com.github.crisacm.animefinder.data.api.model.AnimeFullResponse
import com.github.crisacm.animefinder.data.api.model.AnimeResponse
import com.github.crisacm.module.easyretrofit.model.ApiResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeService {

  @GET("anime")
  suspend fun fetchAnimeList(
    @Query("page") page: Int,
    @Query("limit") limit: Int
  ): ApiResult<AnimeResponse>

  @GET("anime/{id}/full")
  suspend fun fetchAnimeDetail(
    @Path("id") id: Int
  ): ApiResult<AnimeFullResponse>
}
