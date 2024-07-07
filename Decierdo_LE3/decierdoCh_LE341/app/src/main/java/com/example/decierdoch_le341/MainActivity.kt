package com.example.decierdoch_le341

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.decierdoch_le341.controller.ConstraintId
import com.example.decierdoch_le341.controller.Screen
import com.example.decierdoch_le341.data.Gender
import com.example.decierdoch_le341.data.genderList
import com.example.decierdoch_le341.pages.AgeGroupScreen
import com.example.decierdoch_le341.pages.GiftSelectionScreen
import com.example.decierdoch_le341.reusables.ChipGroup
import com.example.decierdoch_le341.reusables.ScreenSubHeading
import com.example.decierdoch_le341.reusables.ScreenTopLabel
import com.example.decierdoch_le341.ui.theme.DecierdoCh_LE341Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.Home.route) {
                composable(Screen.Home.route) {
                    MainScreen(
                        modifier = Modifier,
                        navController = navController
                    )
                }
                composable(
                    route = "${Screen.AgeGroup.route}/{gender}",
                    arguments = listOf(navArgument("gender") { type = NavType.StringType })
                ) {
                    AgeGroupScreen(
                        modifier = Modifier,
                        navController = navController
                    )
                }
                composable(
                    route = "${Screen.GiftSelection.route}/{gender}/{ageGroup}",
                    arguments = listOf(
                        navArgument("gender") { type = NavType.StringType },
                        navArgument("ageGroup") { type = NavType.StringType }
                    )
                ) {
                    GiftSelectionScreen(
                        modifier = Modifier,
                        navController = navController
                    )
                }
            }
        }
    }
}

// MAIN PAGE START

@Composable
fun MainScreen(modifier: Modifier = Modifier, navController: NavController) {
    var selectedGender by remember { mutableStateOf<Gender?>(null) }

    DecierdoCh_LE341Theme {
        Scaffold { innerPadding ->
            Surface(
                color = MaterialTheme.colorScheme.surface,
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
            ) {
                val constraints = ConstraintSet {
                    val chipGroup = createRefFor(ConstraintId.ChipGroup.id)
                    val topLabel = createRefFor(ConstraintId.TopLabel.id)
                    val subHeading = createRefFor(ConstraintId.SubHeading.id)

                    createVerticalChain(
                        topLabel,
                        subHeading.withChainParams(bottomMargin = 8.dp),
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
                        text = "Gift.ly",
                        modifier = modifier.layoutId(ConstraintId.TopLabel.id)
                    )

                    ScreenSubHeading(
                        text = "Select your Gender",
                        modifier = modifier.layoutId(ConstraintId.SubHeading.id)
                    )

                    GenderChipGroup(
                        modifier = modifier.layoutId(ConstraintId.ChipGroup.id),
                        navController = navController,
                        genderList = genderList,
                        selectedGender = selectedGender,
                        onGenderSelected = { gender ->
                            selectedGender = gender
                            navController.navigate("${Screen.AgeGroup.route}/${gender.gender}")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun GenderChipGroup(
    modifier: Modifier = Modifier,
    navController: NavController,
    genderList: List<Gender>,
    selectedGender: Gender?,
    onGenderSelected: (Gender) -> Unit
) {
    ChipGroup(
        modifier = modifier,
        items = genderList,
        isSelected = { it == selectedGender },
        onClick = onGenderSelected,
        label = { it.gender }
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MainScreenPreview(modifier: Modifier = Modifier) {
    val mockNavController = rememberNavController()
    MainScreen(
        modifier = modifier,
        navController = mockNavController
    )
}

// MAIN PAGE END
