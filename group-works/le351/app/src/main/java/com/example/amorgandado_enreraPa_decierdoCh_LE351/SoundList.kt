package com.example.amorgandado_enreraPa_decierdoCh_LE351


data class SoundItem(val name: String, val soundResId: Int)

data class SoundGroup(val header: String, val imageResId: Int, val sounds: List<SoundItem>)
// Modify data here
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