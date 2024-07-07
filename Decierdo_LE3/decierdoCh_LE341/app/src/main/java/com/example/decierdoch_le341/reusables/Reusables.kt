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
fun <T> ChipButton(
    modifier: Modifier = Modifier,
    item: T,
    isSelected: Boolean,
    onClick: () -> Unit,
    label: (T) -> String
) {
    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = {
            Text(text = label(item), maxLines = 1)
        },
        leadingIcon = {
            if (isSelected) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Selected",
                    modifier = modifier.size(FilterChipDefaults.IconSize)
                )
            }
        },
        modifier = modifier.padding(4.dp)
    )
}


@Composable
fun <T> ChipGroup(
    modifier: Modifier = Modifier,
    items: List<T>,
    isSelected: (T) -> Boolean,
    onClick: (T) -> Unit,
    label: (T) -> String
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
            items.forEach { item ->
                ChipButton(
                    item = item,
                    isSelected = isSelected(item),
                    onClick = { onClick(item) },
                    label = label
                )
            }
        }
    }
}
