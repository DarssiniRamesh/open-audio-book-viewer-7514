package com.example.audiobookfrontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.audiobookfrontend.ui.AudioBookApp
import com.example.audiobookfrontend.ui.theme.AudioBookTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val bookmarkManager = com.example.audiobookfrontend.data.BookmarkManager(this)

        setContent {
            AudioBookTheme {
                AudioBookApp(bookmarkManager)
            }
        }
    }
}
