package com.example.audiobookfrontend.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.audiobookfrontend.data.BookRepository
import com.example.audiobookfrontend.model.Book
import com.example.audiobookfrontend.ui.components.AudioPlayer
import com.example.audiobookfrontend.data.BookmarkManager

@Composable
fun BookDetailScreen(bookId: String, navController: NavController, bookmarkManager: BookmarkManager) {
    val book = BookRepository.getBookById(bookId)
    val context = LocalContext.current

    if (book == null) {
        Text("Book not found.", Modifier.padding(16.dp))
        return
    }
    var bookmarked by remember { mutableStateOf(bookmarkManager.isBookBookmarked(book.id)) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (bookmarked) {
                        bookmarkManager.removeBookmark(book.id)
                        Toast.makeText(context, "Removed from Favorites", Toast.LENGTH_SHORT).show()
                    } else {
                        bookmarkManager.addBookmark(book.id)
                        Toast.makeText(context, "Added to Favorites", Toast.LENGTH_SHORT).show()
                    }
                    bookmarked = !bookmarked
                }
            ) {
                Icon(
                    if (bookmarked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Bookmark"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Row {
                Image(
                    painter = rememberAsyncImagePainter(book.coverUrl),
                    contentDescription = null,
                    modifier = Modifier.size(88.dp)
                )
                Spacer(Modifier.width(16.dp))
                Column {
                    Text(book.title, style = MaterialTheme.typography.headlineSmall)
                    Text(book.author, style = MaterialTheme.typography.bodyMedium)
                }
            }
            Spacer(Modifier.height(16.dp))
            Text(book.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = {
                    openPdf(context, book.pdfUrl)
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Open PDF")
            }
            Spacer(Modifier.height(16.dp))
            AudioPlayer(audioUrl = book.audioUrl)
        }
    }
}

private fun openPdf(context: Context, pdfUrl: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.setDataAndType(Uri.parse(pdfUrl), "application/pdf")
    intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
    val chooser = Intent.createChooser(intent, "Open PDF")
    context.startActivity(chooser)
}
