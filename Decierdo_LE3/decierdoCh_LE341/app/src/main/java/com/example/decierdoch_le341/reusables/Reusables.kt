package com.example.decierdoch_le341.reusables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.decierdoch_le341.data.Gender

@Composable
fun ScreenTopLabel(
    modifier: Modifier,
    text: String,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontSize = 36.sp,
        color = MaterialTheme.colorScheme.tertiary
    )
}

@Composable
fun ScreenSubHeading(
    modifier: Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.labelMedium,
        fontSize = 24.sp,
        color = MaterialTheme.colorScheme.tertiary,
        fontWeight = FontWeight.Light
    )
}

@Composable
fun ChipButtons(
    modifier: Modifier = Modifier,
    navController: NavController,
    genderOptions: Gender,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = {
            Text(text = genderOptions.gender, maxLines = 1)
        },
        leadingIcon = {
            if (isSelected) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Gender",
                    modifier = modifier.size(FilterChipDefaults.IconSize)
                )
            }
        },
        modifier = modifier.padding(4.dp)
    )
}

@Composable
fun ChipGroup(
    modifier: Modifier = Modifier,
    navController: NavController,
    genderList: List<Gender>
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            genderList.forEach { gender ->
                ChipButtons(
                    navController = navController,
                    genderOptions = gender,
                    isSelected = false, // Adjust this based on your selection logic,
                    onClick = {}
                )
            }
        }
    }
}