package com.example.shacklehotelbuddy.presentation.lobby

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shacklehotelbuddy.data.repos.SearchRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LobbyViewModel @Inject constructor(
    private val searchRepo: SearchRepo
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        searchRepo.latestSearchLobbyDeskForm().onEach { history ->
            _state.update {
                it.copy(latestSearchHistoryForm = history)
            }
        }.launchIn(viewModelScope)
    }

    fun dispatchActions(action: LobbyDeskAction) {
        _state.update {
            val stagedState = it.copy(showingPickerDialogFor = null)
            when (action) {
                is LobbyDeskAction.OnAdults -> stagedState.copy(
                    currentForm = it.currentForm.copy(
                        adultsCount = action.newValue
                    )
                )

                is LobbyDeskAction.OnCheckIn -> stagedState.copy(
                    currentForm = it.currentForm.copy(
                        checkIn = action.newValue
                    )
                )

                is LobbyDeskAction.OnCheckOut -> stagedState.copy(
                    currentForm = it.currentForm.copy(
                        checkOut = action.newValue
                    )
                )

                is LobbyDeskAction.OnChildren -> stagedState.copy(
                    currentForm = it.currentForm.copy(
                        children = action.newValue
                    )
                )

                is LobbyDeskAction.For -> stagedState.copy(showingPickerDialogFor = action.actionType)
                is LobbyDeskAction.OnSearch -> stagedState.onSearch(withHistory = action.withHistory)
                LobbyDeskAction.DismissDialog -> stagedState.copy(showingPickerDialogFor = null)
            }
        }
    }

    private fun State.onSearch(withHistory: Boolean): State {
        viewModelScope.launch {
            if (!withHistory) {
                searchRepo.searchFor(currentForm)
                _state.update { it.copy(currentForm = LobbyDeskForm()) }
            }
        }
        return this
    }
}




