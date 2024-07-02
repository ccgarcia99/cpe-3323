// File: ui/theme/Type.kt
package com.example.decierdoch_pe331.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.example.decierdoch_pe331.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val ibmPlexSansFontFamily = FontFamily(
    Font(googleFont = GoogleFont("IBM Plex Sans"), fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = GoogleFont("IBM Plex Sans"), fontProvider = provider, weight = FontWeight.Bold)
)

val robotoFontFamily = FontFamily(
    Font(googleFont = GoogleFont("Roboto"), fontProvider = provider, weight = FontWeight.Light),
    Font(googleFont = GoogleFont("Roboto"), fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = GoogleFont("Roboto"), fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = GoogleFont("Roboto"), fontProvider = provider, weight = FontWeight.Bold)
)

// Default Material 3 typography values
val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = robotoFontFamily, fontWeight = FontWeight.Bold),
    displayMedium = baseline.displayMedium.copy(fontFamily = robotoFontFamily, fontWeight = FontWeight.Medium),
    displaySmall = baseline.displaySmall.copy(fontFamily = robotoFontFamily, fontWeight = FontWeight.Normal),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = robotoFontFamily, fontWeight = FontWeight.Bold),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = robotoFontFamily, fontWeight = FontWeight.Medium),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = robotoFontFamily, fontWeight = FontWeight.Normal),
    titleLarge = baseline.titleLarge.copy(fontFamily = robotoFontFamily, fontWeight = FontWeight.Bold),
    titleMedium = baseline.titleMedium.copy(fontFamily = robotoFontFamily, fontWeight = FontWeight.Medium),
    titleSmall = baseline.titleSmall.copy(fontFamily = robotoFontFamily, fontWeight = FontWeight.Normal),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = ibmPlexSansFontFamily, fontWeight = FontWeight.Normal),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = ibmPlexSansFontFamily, fontWeight = FontWeight.Normal),
    bodySmall = baseline.bodySmall.copy(fontFamily = ibmPlexSansFontFamily, fontWeight = FontWeight.Normal),
    labelLarge = baseline.labelLarge.copy(fontFamily = ibmPlexSansFontFamily, fontWeight = FontWeight.Normal),
    labelMedium = baseline.labelMedium.copy(fontFamily = ibmPlexSansFontFamily, fontWeight = FontWeight.Normal),
    labelSmall = baseline.labelSmall.copy(fontFamily = ibmPlexSansFontFamily, fontWeight = FontWeight.Normal),
)
