package com.example.decierdoch_pe332.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.*
import androidx.compose.ui.unit.sp

data class Spacing(
    val defaultSpacing: Dp = 10.dp,

    val extraSmallSpacing: Dp = 4.dp,
    val smallSpacing: Dp = 8.dp,
    val mediumSpacing: Dp = 16.dp,
    val largeSpacing: Dp = 18.dp,

    val marginRegularSpacing: Dp = 24.dp,
    val marginLargeSpacing: Dp = 36.dp,
    val marginExtraLargeSpacing: Dp = 48.dp
)

data class StandardTextSize(
    val defaultTextSize: TextUnit = 14.sp,

    val exSmallTextSize: TextUnit = 8.sp,
    val smallTextSize: TextUnit = 10.sp,
    val mediumTextSize: TextUnit = 18.sp,
    val largeTextSize: TextUnit = 24.sp,
    val exLargeTextSize: TextUnit = 32.sp,

    val smallHeaderText: TextUnit = 18.sp,
    val mediumHeaderText: TextUnit = 24.sp,
    val largeHeaderText: TextUnit = 36.sp,
    val extraLargeHeaderText: TextUnit = 48.sp
)

val LocalSpacing = compositionLocalOf { Spacing() }
val LocalTextSize = compositionLocalOf { StandardTextSize() }