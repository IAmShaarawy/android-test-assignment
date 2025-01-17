package com.example.shacklehotelbuddy.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

object ShackleHotelBuddyTheme {
    val colors: CustomColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: CustomTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

@Composable
fun ShackleHotelBuddyTheme(
    colors: CustomColors = ShackleHotelBuddyTheme.colors,
    typography: CustomTypography = ShackleHotelBuddyTheme.typography,
    content: @Composable () -> Unit
) {
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }
    MaterialTheme(colorScheme = MaterialTheme.colorScheme.copy(primary = ShackleHotelBuddyTheme.colors.teal)) {
        CompositionLocalProvider(
            LocalColors provides rememberedColors,
            LocalTypography provides typography
        ) {
            content()
        }
    }
}