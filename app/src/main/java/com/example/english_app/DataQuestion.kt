package com.example.english_app

data class Question(
    val variants: List<Word>,
    val correctAnswer: Word,
)