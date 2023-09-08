package com.example.shacklehotelbuddy.presentation.lobby

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lobby(viewModel: LobbyViewModel = viewModel()) {
    val state = viewModel.state.collectAsState().value
    val (form, history, showingPickerDialogFor) = state
    Column(
        modifier = Modifier
            .fillMaxSize()

            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillWidth
            )
            .padding(horizontal = 16.dp, vertical = 32.dp),
    ) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
            Text(
                stringResource(R.string.lobby_title),
                style = ShackleHotelBuddyTheme.typography.title.copy(color = ShackleHotelBuddyTheme.colors.white)
            )
            Spacer(modifier = Modifier.height(32.dp))
            LobbyDesk(
                checkIn = form.checkIn?.toString() ?: stringResource(R.string.date_placeholder),
                checkOut = form.checkOut?.toString() ?: stringResource(R.string.date_placeholder),
                adultsCount = form.adultsCount,
                childrenCount = form.children,
                onAction = { viewModel.dispatchActions(LobbyDeskAction.For(it)) }
            )
            if (form.hasValidDateRange == false){
                Spacer(modifier = Modifier.height(4.dp))
                Text(stringResource(R.string.date_error_message), style = ShackleHotelBuddyTheme.typography.captionError)
            }
            if (history != null) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    stringResource(R.string.recent_searches),
                    style = ShackleHotelBuddyTheme.typography.subtitle.copy(color = ShackleHotelBuddyTheme.colors.white)
                )
                Spacer(modifier = Modifier.height(16.dp))
                LatestHistoryBar(
                    checkRange = history.combinedDates,
                    peopleCount = history.combinedPeople,
                    onClick = { viewModel.dispatchActions(LobbyDeskAction.OnSearch(withHistory = true)) })
            }
        }
        SearchButton(
            enabled = state.isSearchButtonEnabled,
            onClick = { viewModel.dispatchActions(LobbyDeskAction.OnSearch()) })

        if (showingPickerDialogFor != null)
            ShowDialogIfApplicableWith(form, showingPickerDialogFor, viewModel::dispatchActions)
    }
}

@Composable
fun ShowDialogIfApplicableWith(
    state: LobbyDeskForm,
    showingPickerDialogFor: LobbyDeskClickActionsType,
    dispatch: (action: LobbyDeskAction) -> Unit
) {
    when (showingPickerDialogFor) {
        LobbyDeskClickActionsType.CheckIn -> DatePickerDialog(
            initValue = state.checkIn,
            onConfirm = { dispatch(LobbyDeskAction.OnCheckIn(it)) },
            onDismiss = { dispatch(LobbyDeskAction.DismissDialog) })

        LobbyDeskClickActionsType.CheckOut -> DatePickerDialog(
            initValue = state.checkOut,
            onConfirm = { dispatch(LobbyDeskAction.OnCheckOut(it)) },
            onDismiss = { dispatch(LobbyDeskAction.DismissDialog) })

        LobbyDeskClickActionsType.Adults -> NumberPicker(
            stringResource(R.string.adults),
            initValue = state.adultsCount,
            onDismissRequest = { dispatch(LobbyDeskAction.DismissDialog) },
            onConfirm = { dispatch(LobbyDeskAction.OnAdults(it)) }
        )

        LobbyDeskClickActionsType.Children -> NumberPicker(
            stringResource(R.string.children),
            initValue = state.children,
            onDismissRequest = { dispatch(LobbyDeskAction.DismissDialog) },
            onConfirm = { dispatch(LobbyDeskAction.OnChildren(it)) }
        )
    }
}
