package com.example.decierdoch_le341

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.decierdoch_le341.controller.ConstraintId
import com.example.decierdoch_le341.controller.Screen
import com.example.decierdoch_le341.data.Gender
import com.example.decierdoch_le341.data.genderList
import com.example.decierdoch_le341.ui.theme.DecierdoCh_LE341Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.Home.route) {
                composable(Screen.Home.route) {
                    // Located in this Kotlin file
                    MainScreen(
                        modifier = Modifier,
                        navController = navController
                    )
                }
                composable(Screen.AgeGroup.route) {
                    //TODO: Create composable screen located in GetAgeGroupPage.kt
                }
                composable(Screen.GiftSelection.route) {
                    //TODO: Create composable screen located in SuggestionsPage.kt
                }
            }
        }
    }
}

// MAIN PAGE START

// Root composable for main page
@Composable
fun MainScreen(modifier: Modifier = Modifier, navController: NavController) {
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
                    TopLabel(modifier = modifier.layoutId(ConstraintId.TopLabel.id))
                    SubHeading(modifier = modifier.layoutId(ConstraintId.SubHeading.id))
                    GenderChipGroup(
                        modifier = modifier.layoutId(ConstraintId.ChipGroup.id),
                        navController = navController,
                        genderList = genderList
                    )
                }
            }
        }
    }
}

@Composable
fun TopLabel(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = "Gift.ly",
        style = MaterialTheme.typography.titleLarge,
        fontSize = 36.sp,
        color = MaterialTheme.colorScheme.tertiary
    )
}

@Composable
fun SubHeading(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = "Select your Gender",
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
    isSelected: Boolean
) {
    FilterChip(
        selected = isSelected,
        onClick = { /*TODO: Implement navigation using navController*/ },
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
fun GenderChipGroup(
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
                    isSelected = false // Adjust this based on your selection logic
                )
            }
        }
    }
}
// MAIN PAGE END

// Preview toolkit
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