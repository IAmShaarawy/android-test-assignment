package com.example.shacklehotelbuddy.presentation.lobby

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.shacklehotelbuddy.R
import com.example.shacklehotelbuddy.ui.theme.ShackleHotelBuddyTheme
import java.text.SimpleDateFormat


//region Lobby Desk
@Composable
fun LobbyDesk(
    modifier: Modifier = Modifier,
    checkIn: String = stringResource(R.string.date_placeholder),
    checkOut: String = stringResource(R.string.date_placeholder),
    adultsCount: Int = 1,
    childrenCount: Int = 0,
    onAction: (LobbyDeskClickActionsType) -> Unit
) {

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = ShackleHotelBuddyTheme.colors.white
    ) {
        Column {
            LobbyDeskItem(
                startCellIconRes = R.drawable.event_upcoming,
                startCellLabelRes = R.string.check_in_date,
                endCellText = checkIn,
                onClick = { onAction(LobbyDeskClickActionsType.CheckIn) }
            )
            HorizontalDivider()
            LobbyDeskItem(
                startCellIconRes = R.drawable.event_available,
                startCellLabelRes = R.string.check_out_date,
                endCellText = checkOut,
                onClick = { onAction(LobbyDeskClickActionsType.CheckOut) }
            )
            HorizontalDivider()
            LobbyDeskItem(
                startCellIconRes = R.drawable.person,
                startCellLabelRes = R.string.adults,
                endCellText = adultsCount.toString(),
                onClick = { onAction(LobbyDeskClickActionsType.Adults) }
            )
            HorizontalDivider()
            LobbyDeskItem(
                startCellIconRes = R.drawable.supervisor_account,
                startCellLabelRes = R.string.children,
                endCellText = childrenCount.toString(),
                onClick = { onAction(LobbyDeskClickActionsType.Children) }
            )
        }
    }
}

@Composable
private fun LobbyDeskItem(
    @DrawableRes startCellIconRes: Int,
    @StringRes startCellLabelRes: Int,
    endCellText: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clickable(
                indication = rememberRipple(),
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            ),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val cellTextStyle =
            ShackleHotelBuddyTheme.typography.bodyMedium.copy(color = ShackleHotelBuddyTheme.colors.grayText)
        CellBox {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = painterResource(startCellIconRes), contentDescription = null)
                Text(stringResource(startCellLabelRes), style = cellTextStyle)
            }
        }
        VerticalDivider()
        CellBox {
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = endCellText,
                style = cellTextStyle
            )
        }
    }
}


context (RowScope)
@Composable
private fun CellBox(
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxSize()
            .padding(16.dp), contentAlignment = Alignment.CenterStart, content = content
    )
}
//endregion

//region Dividers
@Composable
private fun VerticalDivider() {
    Box(
        Modifier
            .fillMaxHeight()
            .width(DividerThickness)
            .background(color = DividerColor)
    )
}

@Composable
private fun HorizontalDivider() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(DividerThickness)
            .background(color = DividerColor)
    )
}

private val DividerThickness = 1.dp
private val DividerColor
    @Composable
    get() = ShackleHotelBuddyTheme.colors.grayBorder
//endregion

//region Latest History Bar
@Composable
fun LatestHistoryBar(
    checkRange: String,
    peopleCount: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable(
                role = Role.Button,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = onClick
            ),
        shape = RoundedCornerShape(8.dp),
        color = ShackleHotelBuddyTheme.colors.white,
    ) {
        val historyTextStyle =
            ShackleHotelBuddyTheme.typography.bodySmall.copy(color = ShackleHotelBuddyTheme.colors.grayText)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.padding(horizontal = 10.dp),
                painter = painterResource(R.drawable.manage_history),
                contentDescription = null,
                tint = ShackleHotelBuddyTheme.colors.teal
            )
            VerticalDivider()
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = checkRange, style = historyTextStyle)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = peopleCount, style = historyTextStyle)
        }
    }
}
//endregion

//region Search Button
@Composable
fun SearchButton(enabled: Boolean = true, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .height(60.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(if(enabled)ShackleHotelBuddyTheme.colors.teal else Color.Gray)
            .fillMaxWidth()
            .clickable(
                enabled = enabled,
                indication = rememberRipple(),
                interactionSource = remember { MutableInteractionSource() },
                role = Role.Button,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.search),
            style = ShackleHotelBuddyTheme.typography.button.copy(color = ShackleHotelBuddyTheme.colors.white)
        )
    }
}
//endregion

//region Date Picker
private val ADayInMillis = (1000 * 60 * 60 * 24)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    initValue: CheckDate?,
    onConfirm: (CheckDate) -> Unit,
    onDismiss: () -> Unit,
    startsFrom: Long = System.currentTimeMillis().minus(ADayInMillis)
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initValue?.toMillis()?.plus(ADayInMillis),
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= startsFrom
            }
        })

    val selectedDate = datePickerState.selectedDateMillis

    androidx.compose.material3.DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                onConfirm(selectedDate?.toCheckDate() ?: return@Button)
                onDismiss()
            }

            ) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismiss()
            }) {
                Text(text = "Cancel")
            }
        }
    ) {
        DatePicker(
            state = datePickerState
        )
    }
}

@SuppressLint("SimpleDateFormat")
fun Long.toCheckDate(): CheckDate {
    val (day, month, year) = SimpleDateFormat("dd-MM-yyyy").format(this).split("-")
    return CheckDate(day = day, month = month, year = year)
}
//endregion

//region Number Picker
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumberPicker(
    title: String,
    initValue: Int,
    onDismissRequest: () -> Unit,
    onConfirm: (Int) -> Unit,
    range: IntRange = 0..10,
) {
    AlertDialog(onDismissRequest = onDismissRequest) {
        var pickerValue by remember { mutableStateOf(initValue) }

        ShackleHotelBuddyTheme {

            Surface(shape = AlertDialogDefaults.shape) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = title, style = ShackleHotelBuddyTheme.typography.title)
                    NumberPicker(
                        modifier = Modifier.fillMaxWidth(),
                        value = pickerValue,
                        range = range,
                        onValueChange = {
                            pickerValue = it
                        },
                        dividersColor = ShackleHotelBuddyTheme.colors.teal
                    )
                    Spacer(Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(onClick = onDismissRequest) {
                            Text("Cancel")
                        }
                        Spacer(Modifier.width(16.dp))
                        Button(onClick = { onConfirm(pickerValue) }) {
                            Text("Ok")
                        }

                    }
                }
            }

        }
    }
}
//endregion


