package com.github.crisacm.animefinder.data.api.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class AnimeResponse(
  val pagination: Pagination,
  val data: List<Anime>
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Pagination(
  @Json(name = "last_visible_page") val lastVisiblePage: Int,
  @Json(name = "has_next_page") val hasNextPage: Boolean,
  @Json(name = "current_page") val currentPage: Int,
  val items: Items
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Items(
  val count: Int,
  val total: Int,
  @Json(name = "per_page") val perPage: Int
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Anime(
  @Json(name = "mal_id") val malId: Int,
  val url: String,
  val images: Images,
  val trailer: Trailer,
  val approved: Boolean,
  val titles: List<Title>,
  val title: String,
  @Json(name = "title_english") val titleEnglish: String?,
  @Json(name = "title_japanese") val titleJapanese: String,
  val title_synonyms: List<String>,
  val type: String,
  val source: String,
  val episodes: Int,
  val status: String,
  val airing: Boolean,
  val aired: Aired,
  val duration: String,
  val rating: String,
  val score: Double,
  @Json(name = "scored_by") val scoredBy: Int,
  val rank: Int,
  val popularity: Int,
  val members: Int,
  val favorites: Int,
  val synopsis: String,
  val background: String?,
  val season: String?,
  val year: Int?,
  val broadcast: Broadcast?,
  val producers: List<Producer>?,
  val licensors: List<Licensor>?,
  val studios: List<Studio>?,
  val genres: List<Genre>?,
  @Json(name = "explicit_genres") val explicitGenres: List<String>?,
  val themes: List<Theme>?,
  val demographics: List<Demographic>?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Images(
  val jpg: Image?,
  val webp: Image?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Image(
  @Json(name = "image_url") val imageUrl: String?,
  @Json(name = "small_image_url") val smallImageUrl: String?,
  @Json(name = "large_image_url") val largeImageUrl: String?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Trailer(
  @Json(name = "youtube_id") val youtubeId: String?,
  val url: String?,
  @Json(name = "embed_url") val embedUrl: String?,
  val images: TrailerImages?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class TrailerImages(
  @Json(name = "image_url") val imageUrl: String?,
  @Json(name = "small_image_url") val smallImageUrl: String?,
  @Json(name = "medium_image_url") val mediumImageUrl: String?,
  @Json(name = "large_image_url") val largeImageUrl: String?,
  @Json(name = "maximum_image_url") val maximumImageUrl: String?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Title(
  val type: String?,
  val title: String?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Aired(
  val from: String?,
  val to: String?,
  val prop: Prop?,
  val string: String?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Prop(
  val from: DateInfo?,
  val to: DateInfo?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class DateInfo(
  val day: Int?,
  val month: Int?,
  val year: Int?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Broadcast(
  val day: String?,
  val time: String?,
  val timezone: String?,
  val string: String?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Producer(
  @Json(name = "mal_id") val malId: Int?,
  val type: String?,
  val name: String?,
  val url: String?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Licensor(
  @Json(name = "mal_id") val malId: Int?,
  val type: String?,
  val name: String?,
  val url: String?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Studio(
  @Json(name = "mal_id") val malId: Int?,
  val type: String?,
  val name: String?,
  val url: String?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Genre(
  @Json(name = "mal_id") val malId: Int?,
  val type: String?,
  val name: String?,
  val url: String?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Theme(
  @Json(name = "mal_id") val malId: Int?,
  val type: String?,
  val name: String?,
  val url: String?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Demographic(
  @Json(name = "mal_id") val malId: Int?,
  val type: String?,
  val name: String?,
  val url: String?
) : Parcelable
