package com.example.audiobookfrontend.ui.components

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

@Composable
fun AudioPlayer(audioUrl: String) {
    val context = LocalContext.current
    var isPlaying by remember { mutableStateOf(false) }
    val exoPlayer = remember { ExoPlayer.Builder(context).build().apply {
        val mediaItem = MediaItem.fromUri(Uri.parse(audioUrl))
        setMediaItem(mediaItem)
        prepare()
    } }

    DisposableEffect(audioUrl) {
        onDispose {
            exoPlayer.release()
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                if (isPlaying) {
                    exoPlayer.pause()
                } else {
                    exoPlayer.play()
                }
                isPlaying = !isPlaying
            }
        ) {
            Text(if (isPlaying) "Pause Audio" else "Play Audio")
        }
    }
}
