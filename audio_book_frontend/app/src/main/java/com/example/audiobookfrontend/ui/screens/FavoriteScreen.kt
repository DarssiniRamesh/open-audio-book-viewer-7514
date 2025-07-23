package com.example.audiobookfrontend.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.audiobookfrontend.data.BookRepository
import com.example.audiobookfrontend.data.BookmarkManager
import com.example.audiobookfrontend.ui.screens.BookItem
import com.example.audiobookfrontend.ui.Screen

@Composable
fun FavoriteScreen(navController: NavController, bookmarkManager: BookmarkManager) {
    val bookmarks = bookmarkManager.getBookmarks()
    val books = BookRepository.getBooks().filter { bookmarks.contains(it.id) }

    if (books.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            Text("No favorites yet")
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(140.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            items(books) { book ->
                BookItem(book, onClick = {
                    navController.navigate(Screen.BookDetail.createRoute(book.id))
                }, isBookmarked = true)
            }
        }
    }
}
