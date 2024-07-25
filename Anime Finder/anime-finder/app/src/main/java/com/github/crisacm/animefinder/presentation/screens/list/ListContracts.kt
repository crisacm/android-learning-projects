package com.github.crisacm.animefinder.presentation.screens.list

import com.github.crisacm.animefinder.data.api.model.Anime
import com.github.crisacm.animefinder.presentation.base.ViewEvent
import com.github.crisacm.animefinder.presentation.base.ViewSideEffect
import com.github.crisacm.animefinder.presentation.base.ViewState

class ListContracts {

    sealed interface Event : ViewEvent {
        data class Search(val query: String) : Event
        data class Select(val id: Long) : Event
    }

    data class State(
        val isLoading: Boolean = false,
        val error: String? = null,
        val loadData: List<Anime> = emptyList(),
    ) : ViewState

    sealed interface Effect : ViewSideEffect {
        sealed interface Navigation : Effect {
            data class ToDetails(val id: Long): Navigation
        }
    }
}
