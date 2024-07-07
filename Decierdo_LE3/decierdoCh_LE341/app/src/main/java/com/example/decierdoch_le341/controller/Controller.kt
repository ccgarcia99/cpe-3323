package com.example.decierdoch_le341.controller

sealed class Screen(
    val route : String
) {
    data object Home : Screen("home")
    data object AgeGroup : Screen("ageGroup")
    data object GiftSelection : Screen("giftSelection")
}

sealed class ConstraintId(
    val id: String
) {
    data object ChipGroup : ConstraintId("chipGroup")
    data object TopLabel : ConstraintId("topLabel")
    data object SubHeading : ConstraintId("subHeading")
}