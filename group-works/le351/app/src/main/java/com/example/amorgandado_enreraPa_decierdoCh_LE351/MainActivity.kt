/*  Name:       Christian Clyde G. Decierdo
                Donyl C. Amorganda
                Paolo Jansen A. Enrera
   Date:        07/09/2024
   Title:       LE 3.51 - List App
   Description: A mobile application that renders a list of sound bites using LazyColumn; The
                implementation of RecyclerViews in Jetpack Compose. Every list element except for
                the header is an interactive element.
* */

package com.example.amorgandado_enreraPa_decierdoCh_LE351

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.amorgandado_enreraPa_decierdoCh_LE351.ui.theme.DecierdoCh_LE351Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DecierdoCh_LE351Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        GroupedSoundsList(groups)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GroupedSoundsList(groups: List<SoundGroup>) {
    val mediaPlayerState = remember { mutableStateOf<MediaPlayer?>(null) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        groups.forEach { group ->
            stickyHeader {
                Column {
                    Image(
                        painter = painterResource(id = group.imageResId),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f)
                    )
                    Text(
                        group.header,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(8.dp)
                    )
                }
            }
            items(group.sounds) { sound ->
                SoundItemView(sound, mediaPlayerState)
            }
        }
    }
}

@Composable
fun SoundItemView(sound: SoundItem, mediaPlayerState: MutableState<MediaPlayer?>) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { playSound(context, sound.soundResId, mediaPlayerState) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = sound.name,
            modifier = Modifier.padding(16.dp)
        )
    }
}

fun playSound(context: Context, soundResId: Int, mediaPlayerState: MutableState<MediaPlayer?>) {
    mediaPlayerState.value?.apply {
        if (isPlaying) {
            stop()
            release()
        }
    }
    val mediaPlayer = MediaPlayer.create(context, soundResId)
    mediaPlayer.setOnCompletionListener {
        it.release()
        mediaPlayerState.value = null
    }
    mediaPlayer.start()
    mediaPlayerState.value = mediaPlayer
}

@Preview(showBackground = true)
@Composable
fun GroupedSoundsListPreview() {
    val weatherSounds = listOf(
        SoundItem("Rain", R.raw.rain_1),
        SoundItem("Thunderstorm", R.raw.thunderstorm),
        SoundItem("Wind", R.raw.winds),
        SoundItem("Ocean", R.raw.ocean_1),
        SoundItem("Birds", R.raw.birds)
    )
    val ringtoneSounds = listOf(
        SoundItem("Classic", R.raw.ringtone_1),
        SoundItem("Beep", R.raw.beep),
        SoundItem("Chime", R.raw.chimes),
        SoundItem("Alarm", R.raw.siren),
        SoundItem("Bell", R.raw.bell)
    )
    val groups = listOf(
        SoundGroup("Weather Sounds", R.drawable._681495279040607, weatherSounds),
        SoundGroup("Ringtone Sounds", R.drawable._708470532817406, ringtoneSounds)
    )
    GroupedSoundsList(groups)
}
