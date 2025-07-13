package com.example.newsapp.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.news.usecase.GetNewsByQueryUseCase
import com.example.newsapp.common.search.SearchEvent
import com.example.newsapp.common.search.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getNewsByQueryUseCase: GetNewsByQueryUseCase
): ViewModel() {
    private val _state = mutableStateOf(SearchState())
    val state get() = _state.value


    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateQuery -> {
                _state.value = state.copy(query = event.query)
            }

            is SearchEvent.SearchNews -> {
                _state.value = state.copy(
                    articles = getNewsByQueryUseCase.searchNews(
                        query = state.query,
                        sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
                    )
                )
            }
        }

    }


}