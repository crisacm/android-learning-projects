package com.github.crisacm.animefinder.presentation.screens.list

import com.github.crisacm.animefinder.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor() :
    BaseViewModel<ListContracts.Event, ListContracts.State, ListContracts.Effect>() {

    override fun setInitialState(): ListContracts.State =
        ListContracts.State()

    override fun handleEvent(event: ListContracts.Event) {
        when (event) {
            is ListContracts.Event.Search -> {}
            is ListContracts.Event.Select -> {}
        }
    }
}
