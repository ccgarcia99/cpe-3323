package com.example.decierdo_pe31.ui.theme

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun defaultTextFieldColors(): TextFieldColors {
    val tFDefaultParams = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color.Blue,
        focusedLabelColor = Color.Blue,
        unfocusedBorderColor = Color.Black,
        unfocusedLabelColor = Color.Black,
        cursorColor = Color.Black
    )
    return tFDefaultParams
}
