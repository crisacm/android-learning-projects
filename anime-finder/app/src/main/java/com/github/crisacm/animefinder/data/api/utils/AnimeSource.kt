package com.github.crisacm.animefinder.data.api.utils

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Error
import androidx.paging.PagingState
import com.github.crisacm.animefinder.data.api.model.Anime
import com.github.crisacm.animefinder.data.api.service.AnimeClient
import com.github.crisacm.animefinder.data.api.service.AnimeService
import com.github.crisacm.module.easyretrofit.model.ApiResult
import okio.IOException
import retrofit2.HttpException

class AnimeSource(
  private val animeService: AnimeService
) : PagingSource<Int, Anime>() {

  override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
    return state.anchorPosition
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
    return try {
      val nexPage = params.key ?: 1
      when (val animeList = animeService.fetchAnimeList(nexPage, AnimeClient.PAGING_SIZE)) {
        is ApiResult.Success -> {
          LoadResult.Page(
            data = animeList.data.data,
            prevKey = if (nexPage == 1) null else nexPage - 1,
            nextKey = if (animeList.data.data.isEmpty()) null else nexPage + 1
          )
        }
        is ApiResult.Failed -> {
          Error(Exception(animeList.message))
        }

        is ApiResult.Error -> {
          Error(animeList.t)
        }
      }
    } catch (e: IOException) {
      Error(e)
    } catch (e: HttpException) {
      Error(e)
    }
  }
}
