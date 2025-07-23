package com.example.audiobookfrontend.data

import android.content.Context

// PUBLIC_INTERFACE
class BookmarkManager(context: Context) {
    private val prefs = context.getSharedPreferences("bookmarks", Context.MODE_PRIVATE)

    // PUBLIC_INTERFACE
    fun getBookmarks(): Set<String> = prefs.getStringSet("favorites", emptySet()) ?: emptySet()

    // PUBLIC_INTERFACE
    fun isBookBookmarked(bookId: String): Boolean = getBookmarks().contains(bookId)

    // PUBLIC_INTERFACE
    fun addBookmark(bookId: String) {
        val current = getBookmarks().toMutableSet()
        current.add(bookId)
        prefs.edit().putStringSet("favorites", current).apply()
    }

    // PUBLIC_INTERFACE
    fun removeBookmark(bookId: String) {
        val current = getBookmarks().toMutableSet()
        current.remove(bookId)
        prefs.edit().putStringSet("favorites", current).apply()
    }
}
