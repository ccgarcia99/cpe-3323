package com.example.decierdoch_le341.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import com.example.decierdoch_le341.R
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
                val selectionSummary = createRefFor(ConstraintId.Summary.id)
                val itemRow = createRefFor(ConstraintId.ItemRow.id)
                val giftSuggestions = createRefFor(ConstraintId.Suggestions.id)

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

                constrain(itemRow) {
                    top.linkTo(selectionSummary.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }

                constrain(giftSuggestions) {
                    top.linkTo(itemRow.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                }
            }

            ConstraintLayout(
                constraintSet = constraints,
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
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
                    modifier = modifier.layoutId(ConstraintId.Summary.id),
                    gender = selectedGender,
                    ageGroup = selectedAgeGroup
                )
                ItemRow(
                    modifier = modifier.layoutId(ConstraintId.ItemRow.id),
                    gender = selectedGender,
                    ageGroup = selectedAgeGroup
                )
                GiftSuggestions(
                    modifier = modifier.layoutId(ConstraintId.Suggestions.id),
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
fun ItemRow(modifier: Modifier = Modifier, gender: String?, ageGroup: String?) {
    val imageResources = remember(gender, ageGroup) {
        when {
            gender == "Male" && ageGroup == "Infant" -> listOf(R.drawable.toy_trucks, R.drawable.blanket)
            gender == "Female" && ageGroup == "Infant" -> listOf(R.drawable.doll, R.drawable.blanket)
            gender == "Male" && ageGroup == "Adolescent" -> listOf(R.drawable.baseball_jersey)
            gender == "Female" && ageGroup == "Adolescent" -> listOf(R.drawable.softball_jersey)
            gender == "Male" && ageGroup == "Adult" -> listOf(R.drawable.watch, R.drawable.tie)
            gender == "Female" && ageGroup == "Adult" -> listOf(R.drawable.earrings, R.drawable.watch)
            gender == "Other" -> listOf(R.drawable.toy_trucks, R.drawable.blanket, R.drawable.doll,
                R.drawable.baseball_jersey, R.drawable.softball_jersey,
                R.drawable.watch, R.drawable.earrings)
            else -> emptyList()
        }
    }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        items(imageResources) { imageResource ->
            ItemBox(sourceImg = imageResource)
        }
    }
}

@Composable
fun ItemBox(modifier: Modifier = Modifier, sourceImg: Int) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .size(120.dp)
            .padding(4.dp)
    ) {
        Image(
            painter = painterResource(id = sourceImg),
            contentDescription = "Gift",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun GiftSuggestions(modifier: Modifier = Modifier, gender: String?, ageGroup: String?) {
    val giftSuggestions = remember {
        mapOf(
            "Male" to mapOf(
                "Infant" to listOf("toy trucks", "blanket"),
                "Adolescent" to listOf("baseball jersey"),
                "Adult" to listOf("tie", "watch")
            ),
            "Female" to mapOf(
                "Infant" to listOf("doll", "blanket"),
                "Adolescent" to listOf("softball jersey"),
                "Adult" to listOf("earrings", "watch")
            )
        )
    }

    val suggestions = when {
        gender == null || ageGroup == null -> emptyList()
        gender == "Other" -> giftSuggestions.values.flatMap { it.values.flatten() }.distinct()
        else -> giftSuggestions[gender]?.get(ageGroup) ?: emptyList()
    }

    Column(modifier = modifier) {
        Text(
            text = "Gift Suggestions:",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (suggestions.isNotEmpty()) {
            suggestions.forEach { gift ->
                Text(text = "• $gift")
            }
        } else {
            Text(text = "No suggestions available for the selected combination: $gender - $ageGroup")
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewUI(modifier: Modifier = Modifier) {
    DecierdoCh_LE341Theme {
        ItemBox(modifier = modifier, sourceImg = R.drawable.doll)
    }
}