package com.example.audiobookfrontend.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.audiobookfrontend.data.BookRepository
import com.example.audiobookfrontend.model.Book
import com.example.audiobookfrontend.ui.Screen
import com.example.audiobookfrontend.data.BookmarkManager

@Composable
fun LibraryScreen(navController: NavController, bookmarkManager: BookmarkManager) {
    var searchQuery by remember { mutableStateOf("") }
    val books = remember(searchQuery) { BookRepository.searchBooks(searchQuery) }

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search books") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(140.dp),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight()
        ) {
            items(books) { book ->
                BookItem(book, onClick = {
                    navController.navigate(Screen.BookDetail.createRoute(book.id))
                }, isBookmarked = bookmarkManager.isBookBookmarked(book.id))
            }
        }
    }
}

@Composable
fun BookItem(book: Book, onClick: () -> Unit, isBookmarked: Boolean) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(book.coverUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
            )
            Text(book.title, style = MaterialTheme.typography.bodyLarge, maxLines = 2)
            Text(book.author, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary)
            if (isBookmarked) {
                Icon(Icons.Default.Favorite, "Bookmarked", tint = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
