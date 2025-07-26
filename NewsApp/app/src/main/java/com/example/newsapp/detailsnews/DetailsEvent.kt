package com.example.newsapp.detailsnews

sealed class DetailsEvent {
    object onBookmarkClick : DetailsEvent()
}

