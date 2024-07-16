/*  Name:       Christian Clyde G. Decierdo
                Donyl C. Amorganda
                Paolo Jansen A. Enrera
   Date:        07/09/2024
   Title:       LE 3.52 - WebView App
   Description: A mobile application that renders a list of links using AlertDialog. TextButtons are
                rendered within the AlertDialog composable, enabling the users to be redirected to
                respective websites when the text buttons are clicked.
* */

package com.example.amorgandado_enrerapa_decierdoch_le352

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.amorgandado_enrerapa_decierdoch_le352.ui.theme.AmorgandaDo_enreraPa_decierdoCh_LE352Theme

class MainActivity : ComponentActivity() {
    private val modifier: Modifier = Modifier
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen(modifier = modifier)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier) {
    AmorgandaDo_enreraPa_decierdoCh_LE352Theme {
        val showDialog = remember { mutableStateOf(false) }
        val selectedUrl = remember { mutableStateOf<String?>(null) }

        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(
                        text = "Links",
                        fontWeight = FontWeight.Light
                    )
                })
            }
        ) { innerPadding ->
            Surface(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (selectedUrl.value == null) {
                    Column(
                        modifier = modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ElevatedButton(
                            onClick = { showDialog.value = true }
                        ) {
                            Text(
                                text = "Click for links!"
                            )
                        }

                        if (showDialog.value) {
                            LinksAlertDialog(
                                onDismissRequest = { showDialog.value = false },
                                onLinkClick = { url ->
                                    selectedUrl.value = url
                                    showDialog.value = false
                                },
                                dialogTitle = "Important Links",
                                dialogText = "Here are some important links you should check out.",
                                icon = Icons.Default.Info
                            )
                        }
                    }
                } else {
                    WebPage(url = selectedUrl.value!!, onBack = { selectedUrl.value = null })
                }
            }
        }
    }
}

@Composable
fun LinksAlertDialog(
    onDismissRequest: () -> Unit,
    onLinkClick: (String) -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    val links = listOf(
        "Instagram" to "https://www.instagram.com",
        "Google" to "https://www.google.com",
        "JSTOR" to "https://www.jstor.org",
        "YouTube" to "https://www.youtube.com",
        "LinkedIn" to "https://www.linkedin.com"
    )

    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {},
        icon = {
            Icon(icon, contentDescription = "Dialog Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Column {
                Text(text = dialogText)
                links.forEach { (name, url) ->
                    TextButton(onClick = { onLinkClick(url) }) {
                        Text(text = name)
                    }
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = "Dismiss")
            }
        }
    )
}

@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebPage(url: String, onBack: () -> Unit) {
    Column {
        TopAppBar(
            title = { Text(text = url) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
        AndroidView(factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(url)
            }
        }, update = { webView ->
            webView.loadUrl(url)
        })
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun UIPreview(modifier: Modifier = Modifier) {
    MainScreen(modifier = modifier)
}
