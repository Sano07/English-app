package com.example.english_app

data class Word(
    val original: String,
    val translate: String,
    var learned: Boolean = false
)