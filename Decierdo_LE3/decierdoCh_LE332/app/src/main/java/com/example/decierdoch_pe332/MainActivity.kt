package com.example.decierdoch_pe332

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.decierdoch_pe332.ui.theme.DecierdoCh_PE332Theme
import com.example.decierdoch_pe332.ui.theme.LocalSpacing
import com.example.decierdoch_pe332.ui.theme.LocalTextSize

data class Meal(val name: String, val side: Sides)
data class Sides(val name: String, val description: String)

val mealsList = listOf(
    Meal("Burger", Sides("Fries", "A plate of crispy fries with a side of ketchup")),
    Meal("Pasta", Sides("Bread-sticks", "A plate of bread-sticks basted with butter garlic and garnished with chives")),
    Meal("Orange Chicken", Sides("Vegetable stir-fry", "A medley of vegetables seasoned with oriental herbs and spices"))
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RootComposable()
        }
    }
}

@Composable
fun RootComposable(modifier: Modifier = Modifier) {
    val spacing = LocalSpacing.current
    val myTextSize = LocalTextSize.current
    DecierdoCh_PE332Theme(
        darkTheme = false
    ) {
        Scaffold { innerPadding ->
            Surface(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.surface
            ) {
                ConstraintLayout {
                    val (labelRef, subHeadingRef, chipGroup, cardRef) = createRefs()

                    TopLabel(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .constrainAs(labelRef) {
                                top.linkTo(parent.top, margin = spacing.marginRegularSpacing)
                                start.linkTo(parent.start, margin = spacing.marginRegularSpacing)
                                end.linkTo(parent.end, margin = spacing.marginRegularSpacing)
                            },
                        customTextSize = myTextSize.extraLargeHeaderText
                    )

                    SubHeading(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .constrainAs(subHeadingRef) {
                                top.linkTo(labelRef.bottom, margin = spacing.smallSpacing)
                                start.linkTo(parent.start, margin = spacing.marginRegularSpacing)
                                end.linkTo(parent.end, margin = spacing.marginRegularSpacing)
                            },
                        customTextSize = myTextSize.smallHeaderText
                    )

                    var selectedMeal by remember { mutableStateOf(mealsList.first()) }

                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .constrainAs(chipGroup) {
                                top.linkTo(subHeadingRef.bottom, margin = spacing.smallSpacing)
                                start.linkTo(parent.start, margin = spacing.marginRegularSpacing)
                                end.linkTo(parent.end, margin = spacing.marginRegularSpacing)
                            },
                        horizontalArrangement = Arrangement.Center
                    ) {
                        mealsList.forEach { meal ->
                            ChipOptions(
                                meal = meal,
                                modifier = Modifier.padding(horizontal = 8.dp),
                                onClick = { selectedMeal = meal },
                                isSelected = selectedMeal == meal
                            )
                        }
                    }

                    val imageId = when (selectedMeal.side.name) {
                        "Fries" -> R.drawable.fries
                        "Bread-sticks" -> R.drawable.breadsticks
                        "Vegetable stir-fry" -> R.drawable.vegetable_stir_fry
                        else -> R.drawable.fries
                    }

                    SideDishCard(
                        modifier = modifier
                            .constrainAs(cardRef) {
                                top.linkTo(chipGroup.bottom, margin = spacing.smallSpacing)
                                start.linkTo(parent.start, margin = spacing.marginRegularSpacing)
                                end.linkTo(parent.end, margin = spacing.marginRegularSpacing)
                            },
                        imageId = imageId,
                        side = selectedMeal.side
                    )
                }
            }
        }
    }
}

@Composable
fun SideDishCard(modifier: Modifier, imageId: Int, side: Sides) {
    val spacing = LocalSpacing.current
    val myTextSize = LocalTextSize.current
    Box(
        modifier = modifier.wrapContentSize(Alignment.Center)
    ) {
        Surface(
            color = MaterialTheme.colorScheme.secondaryContainer,
            modifier = modifier
                .wrapContentHeight()
                .fillMaxWidth(0.9f),
            shape = RoundedCornerShape(20.dp),
            shadowElevation = 8.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(top = spacing.smallSpacing)
            ) {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.92f)
                        .aspectRatio(16f / 9f)
                ) {
                    Image(
                        painter = painterResource(id = imageId),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Text(
                    text = side.name,
                    fontSize = myTextSize.mediumTextSize,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = modifier.padding(top = spacing.mediumSpacing, bottom = spacing.mediumSpacing)
                )
                Text(
                    text = side.description,
                    modifier = modifier.padding(all = spacing.mediumSpacing)
                )
            }
        }
    }
}

@Composable
fun TopLabel(modifier: Modifier, customTextSize: TextUnit) {
    Text(
        text = "Little Sous",
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary,
        fontSize = customTextSize
    )
}

@Composable
fun SubHeading(modifier: Modifier, customTextSize: TextUnit) {
    Text(
        text = "your personal side-dish curator",
        textAlign = TextAlign.End,
        modifier = modifier,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.secondary,
        fontSize = customTextSize,
        fontStyle = FontStyle.Italic,
        letterSpacing = 0.5.sp
    )
}

@Composable
fun ChipOptions(modifier: Modifier = Modifier, meal: Meal, onClick: () -> Unit, isSelected: Boolean) {
    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = { Text(text = meal.name, maxLines = 1) },
        modifier = modifier,
        leadingIcon = {
            if (isSelected) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "some icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        }
    )
}

@Preview(
    showBackground = true
)
@Composable
fun ComponentPreview() {
    DecierdoCh_PE332Theme {
        SideDishCard(modifier = Modifier, imageId = R.drawable.fries, side = mealsList[0].side)
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AppPreview() {
    RootComposable()
}
