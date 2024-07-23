package com.github.crisacm.animefinder.navigation

import kotlinx.serialization.Serializable

@Serializable
object ListDestination

@Serializable
data class InfoDestination(var animeId: Long)
