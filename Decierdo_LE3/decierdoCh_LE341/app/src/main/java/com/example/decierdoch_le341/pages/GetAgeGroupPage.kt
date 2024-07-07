package com.example.decierdoch_le341.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.decierdoch_le341.controller.ConstraintId
import com.example.decierdoch_le341.controller.Screen
import com.example.decierdoch_le341.data.AgeGroup
import com.example.decierdoch_le341.data.ageGroup
import com.example.decierdoch_le341.reusables.ChipGroup
import com.example.decierdoch_le341.reusables.ScreenSubHeading
import com.example.decierdoch_le341.reusables.ScreenTopLabel
import com.example.decierdoch_le341.ui.theme.DecierdoCh_LE341Theme

@Composable
fun AgeGroupScreen(modifier: Modifier = Modifier, navController: NavController) {
    val selectedGender = navController.currentBackStackEntry?.arguments?.getString("gender")
    var selectedAgeGroup by remember { mutableStateOf<AgeGroup?>(null) }

    DecierdoCh_LE341Theme {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            val constraints = ConstraintSet {
                val topLabel = createRefFor(ConstraintId.TopLabel.id)
                val subHeading = createRefFor(ConstraintId.SubHeading.id)
                val chipGroup = createRefFor(ConstraintId.ChipGroup.id)

                createVerticalChain(
                    topLabel,
                    subHeading.withChainParams(bottomMargin = 16.dp),
                    chipGroup,
                    chainStyle = ChainStyle.Packed
                )

                constrain(topLabel) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }

                constrain(subHeading) {
                    top.linkTo(topLabel.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }

                constrain(chipGroup) {
                    top.linkTo(subHeading.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            }

            ConstraintLayout(
                constraintSet = constraints,
                modifier = modifier.fillMaxSize()
            ) {
                ScreenTopLabel(
                    modifier = modifier.layoutId(ConstraintId.TopLabel.id),
                    text = "Gift.ly"
                )
                ScreenSubHeading(
                    modifier = modifier.layoutId(ConstraintId.SubHeading.id),
                    text = "Select your age group."
                )
                AgeGroupChipGroup(
                    modifier = modifier.layoutId(ConstraintId.ChipGroup.id),
                    navController = navController,
                    ageGroups = ageGroup,
                    selectedAgeGroup = selectedAgeGroup,
                    onAgeGroupSelected = { ageGroup ->
                        selectedAgeGroup = ageGroup
                        navController.navigate("${Screen.GiftSelection.route}/${selectedGender}/${ageGroup.ageGroup}")
                    }
                )
            }
        }
    }
}

@Composable
fun AgeGroupChipGroup(
    modifier: Modifier = Modifier,
    navController: NavController,
    ageGroups: List<AgeGroup>,
    selectedAgeGroup: AgeGroup?,
    onAgeGroupSelected: (AgeGroup) -> Unit
) {
    ChipGroup(
        modifier = modifier,
        items = ageGroups,
        isSelected = { it == selectedAgeGroup },
        onClick = onAgeGroupSelected,
        label = { it.ageGroup }
    )
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AgeGroupScreenPreview(
    modifier: Modifier = Modifier,
) {
    val mockNavController = rememberNavController()
    AgeGroupScreen(navController = mockNavController)
}