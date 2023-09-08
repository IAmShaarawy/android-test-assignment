package com.example.shacklehotelbuddy.presentation.lobby

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.shacklehotelbuddy.R
import java.text.SimpleDateFormat

enum class LobbyDeskClickActionsType {
    CheckIn, CheckOut, Adults, Children
}

data class CheckDate(
    val day: String,
    val month: String,
    val year: String
) {
    override fun toString(): String {
        return "$day / $month / $year"
    }

    @SuppressLint("SimpleDateFormat")
    fun toMillis(): Long? = SimpleDateFormat("dd / MM / yyyy").parse(toString())?.time

    companion object {
        @SuppressLint("SimpleDateFormat")
        fun fromString(data: String?): CheckDate? = data?.let {
            SimpleDateFormat("dd / MM / yyyy").parse(
                it
            )?.time?.toCheckDate()
        }

    }

}

data class LobbyDeskForm(
    val checkIn: CheckDate? = null,
    val checkOut: CheckDate? = null,
    val adultsCount: Int = 0,
    val children: Int = 0
) {
    val combinedDates: String get() = "$checkIn - $checkOut"
    val combinedPeople: String
        @Composable get() = "$adultsCount ${stringResource(R.string.adults)}, $children ${
            stringResource(
                R.string.children
            )
        }"

    val hasValidDateRange: Boolean?
        get() = when {
            checkIn == null -> null
            checkOut == null -> null
            checkOut.toMillis()!! >= checkIn.toMillis()!! -> true
            else -> false
        }
}

data class State(
    val currentForm: LobbyDeskForm = LobbyDeskForm(),
    val latestSearchHistoryForm: LobbyDeskForm? = null,
    val showingPickerDialogFor: LobbyDeskClickActionsType? = null,
) {
    val isSearchButtonEnabled: Boolean
        get() = currentForm.adultsCount > 0 && currentForm.hasValidDateRange == true
}

sealed interface LobbyDeskAction {
    class OnCheckIn(val newValue: CheckDate) : LobbyDeskAction

    class OnCheckOut(val newValue: CheckDate) : LobbyDeskAction

    class OnAdults(val newValue: Int) : LobbyDeskAction

    class OnChildren(val newValue: Int) : LobbyDeskAction

    class For(val actionType: LobbyDeskClickActionsType) : LobbyDeskAction

    class OnSearch(val withHistory: Boolean = false) : LobbyDeskAction

    data object DismissDialog : LobbyDeskAction
}