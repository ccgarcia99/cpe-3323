package com.example.decierdoch_le341.data

data class AgeGroup(val ageGroup: String)
data class Gender(val gender: String, val ageGroup: List<AgeGroup>)

val ageGroup = listOf(
    AgeGroup("Infant"),
    AgeGroup("Adolescent"),
    AgeGroup("Adult")
)

val genderList = listOf(
    Gender("Male", ageGroup),
    Gender("Female", ageGroup),
    Gender("Other", ageGroup)
)