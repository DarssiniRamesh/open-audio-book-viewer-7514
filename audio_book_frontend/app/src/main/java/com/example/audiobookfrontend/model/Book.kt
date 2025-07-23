package com.example.audiobookfrontend.model

// PUBLIC_INTERFACE
data class Book(
    val id: String,
    val title: String,
    val author: String,
    val pdfUrl: String,
    val audioUrl: String,
    val coverUrl: String,
    val description: String
)
