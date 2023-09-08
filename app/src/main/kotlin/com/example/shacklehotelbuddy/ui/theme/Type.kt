package com.example.shacklehotelbuddy.ui.theme

import android.icu.text.CaseMap.Title
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.shacklehotelbuddy.R

@Immutable
data class CustomTypography constructor(
    val title: TextStyle = TextStyle(
        fontFamily = FontFamily(fonts = listOf(Font(R.font.circular_std))),
        fontSize = 44.sp,
        lineHeight = 48.sp,
    ),
    val subtitle: TextStyle = TextStyle(
        fontFamily = FontFamily(fonts = listOf(Font(R.font.circular_std))),
        fontSize = 20.sp,
        lineHeight = 22.sp,
    ),
    val bodySmall: TextStyle = TextStyle(
        fontFamily = FontFamily(fonts = listOf(Font(R.font.circular_std))),
        fontSize = 12.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
    val bodyMedium: TextStyle = TextStyle(
        fontFamily = FontFamily(fonts = listOf(Font(R.font.circular_std))),
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),

    val captionError: TextStyle = TextStyle(
        fontFamily = FontFamily(fonts = listOf(Font(R.font.circular_std))),
        fontSize = 12.sp,
        lineHeight = 10.sp,
        letterSpacing = 0.5.sp,
        color = Color.Red
    ),

    val button: TextStyle = TextStyle(
        fontFamily = FontFamily(fonts = listOf(Font(R.font.circular_std))),
        fontSize = 18.sp,
        lineHeight = 21.6.sp,
    ),
){

}

val LocalTypography = staticCompositionLocalOf {
    CustomTypography()
}