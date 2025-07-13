package com.example.newsapp.common.search

sealed class SearchEvent {
    data class UpdateQuery(val query: String) : SearchEvent()
    object SearchNews : SearchEvent()

}