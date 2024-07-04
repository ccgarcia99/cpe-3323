/*
* Name: Christian Clyde G. Decierdo
* File: Decierdo_PE3.2.2 - Quote of the Day / Presets
* Desc: Contains custom defined presets for the application.
* */

package com.example.decierdo_pe322.ui.theme

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)

class Presets{
    // Top App Bar presets
    val topAppBarPresets: @Composable (String) -> Unit = { titleText ->
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = titleText,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Light
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }
}
