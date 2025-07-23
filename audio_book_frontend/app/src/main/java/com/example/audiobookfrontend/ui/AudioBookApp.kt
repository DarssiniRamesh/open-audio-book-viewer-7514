package com.example.audiobookfrontend.ui

import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.example.audiobookfrontend.data.BookmarkManager
import com.example.audiobookfrontend.ui.screens.FavoriteScreen
import com.example.audiobookfrontend.ui.screens.LibraryScreen
import com.example.audiobookfrontend.ui.screens.BookDetailScreen

// Define navigation routes
sealed class Screen(val route: String) {
    object Library : Screen("library")
    object Favorites : Screen("favorites")
    object BookDetail : Screen("details/{bookId}") {
        fun createRoute(bookId: String) = "details/$bookId"
    }
}

// PUBLIC_INTERFACE
@Composable
fun AudioBookApp(bookmarkManager: BookmarkManager) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Library.route,
            modifier = Modifier.then(Modifier.padding(innerPadding))
        ) {
            composable(Screen.Library.route) {
                LibraryScreen(navController, bookmarkManager)
            }
            composable(Screen.Favorites.route) {
                FavoriteScreen(navController, bookmarkManager)
            }
            composable(Screen.BookDetail.route) { backStackEntry ->
                val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
                BookDetailScreen(bookId, navController, bookmarkManager)
            }
        }
    }
}

// PUBLIC_INTERFACE
@Composable
fun BottomBar(navController: NavHostController) {
    NavigationBar {
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = currentBackStackEntry?.destination?.route
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Library") },
            label = { Text("Library") },
            selected = currentRoute == Screen.Library.route,
            onClick = { navController.navigate(Screen.Library.route) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
            label = { Text("Favorites") },
            selected = currentRoute == Screen.Favorites.route,
            onClick = { navController.navigate(Screen.Favorites.route) }
        )
    }
}
