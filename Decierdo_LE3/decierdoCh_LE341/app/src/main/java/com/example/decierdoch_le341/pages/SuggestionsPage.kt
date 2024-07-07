package com.example.decierdoch_le341.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import com.example.decierdoch_le341.controller.ConstraintId
import com.example.decierdoch_le341.reusables.ScreenSubHeading
import com.example.decierdoch_le341.reusables.ScreenTopLabel
import com.example.decierdoch_le341.ui.theme.DecierdoCh_LE341Theme

@Composable
fun GiftSelectionScreen(modifier: Modifier = Modifier, navController: NavController) {
    val selectedGender = navController.currentBackStackEntry?.arguments?.getString("gender")
    val selectedAgeGroup = navController.currentBackStackEntry?.arguments?.getString("ageGroup")

    DecierdoCh_LE341Theme {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            val constraints = ConstraintSet {
                val topLabel = createRefFor(ConstraintId.TopLabel.id)
                val subHeading = createRefFor(ConstraintId.SubHeading.id)
                val selectionSummary = createRefFor("selectionSummary")
                val giftSuggestions = createRefFor("giftSuggestions")

                constrain(topLabel) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }

                constrain(subHeading) {
                    top.linkTo(topLabel.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }

                constrain(selectionSummary) {
                    top.linkTo(subHeading.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }

                constrain(giftSuggestions) {
                    top.linkTo(selectionSummary.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                }
            }

            ConstraintLayout(
                constraintSet = constraints,
                modifier = modifier.fillMaxSize().padding(16.dp)
            ) {
                ScreenTopLabel(
                    modifier = modifier.layoutId(ConstraintId.TopLabel.id),
                    text = "Gift.ly"
                )
                ScreenSubHeading(
                    modifier = modifier.layoutId(ConstraintId.SubHeading.id),
                    text = "Gift Suggestions"
                )
                SelectionSummary(
                    modifier = modifier.layoutId("selectionSummary"),
                    gender = selectedGender,
                    ageGroup = selectedAgeGroup
                )
                GiftSuggestions(
                    modifier = modifier.layoutId("giftSuggestions"),
                    gender = selectedGender,
                    ageGroup = selectedAgeGroup
                )
            }
        }
    }
}

@Composable
fun SelectionSummary(modifier: Modifier = Modifier, gender: String?, ageGroup: String?) {
    Column(modifier = modifier) {
        Text(
            text = "Your Selections:",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Gender: ${gender ?: "Not selected"}")
        Text(text = "Age Group: ${ageGroup ?: "Not selected"}")
    }
}

@Composable
fun GiftSuggestions(modifier: Modifier = Modifier, gender: String?, ageGroup: String?) {
    // This is a placeholder. In a real app, you'd fetch gift suggestions based on gender and age group.
    val giftSuggestions = listOf(
        "Book",
        "Toy",
        "Clothing",
        "Electronics",
        "Gift Card"
    )

    Column(modifier = modifier) {
        Text(
            text = "Gift Suggestions:",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        giftSuggestions.forEach { gift ->
            Text(text = "• $gift")
        }
    }
}