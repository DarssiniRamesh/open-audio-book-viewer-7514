package com.example.audiobookfrontend.data

import com.example.audiobookfrontend.model.Book

// PUBLIC_INTERFACE
object BookRepository {
    // Mock data; in real app, fetch from network.
    private val books = listOf(
        Book(
            id = "1",
            title = "Pride and Prejudice",
            author = "Jane Austen",
            pdfUrl = "https://www.gutenberg.org/files/1342/1342-pdf.pdf",
            audioUrl = "https://www.sample-videos.com/audio/mp3/crowd-cheering.mp3",
            coverUrl = "https://covers.openlibrary.org/b/id/8231856-L.jpg",
            description = "A novel of manners and marriage in early 19th-century England."
        ),
        Book(
            id = "2",
            title = "Adventures of Sherlock Holmes",
            author = "Arthur Conan Doyle",
            pdfUrl = "https://www.gutenberg.org/files/1661/1661-pdf.pdf",
            audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",
            coverUrl = "https://covers.openlibrary.org/b/id/8228691-L.jpg",
            description = "Detective stories of the brilliant Sherlock Holmes."
        ),
        Book(
            id = "3",
            title = "Frankenstein",
            author = "Mary Shelley",
            pdfUrl = "https://www.gutenberg.org/files/84/84-pdf.pdf",
            audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3",
            coverUrl = "https://covers.openlibrary.org/b/id/9251788-L.jpg",
            description = "The Gothic tale of Dr. Frankenstein and his monstrous creation."
        )
        // Add more as needed.
    )

    // PUBLIC_INTERFACE
    fun getBooks(): List<Book> = books

    // PUBLIC_INTERFACE
    fun searchBooks(query: String): List<Book> {
        return if (query.isBlank()) {
            books
        } else {
            books.filter { it.title.contains(query, ignoreCase = true) || it.author.contains(query, ignoreCase = true) }
        }
    }

    // PUBLIC_INTERFACE
    fun getBookById(id: String): Book? = books.find { it.id == id }
}
