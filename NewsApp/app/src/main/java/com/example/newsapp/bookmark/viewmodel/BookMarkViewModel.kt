package com.example.newsapp.bookmark.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.domain.news.model.NewsEntity
import com.example.domain.news.usecase.GetNewsByQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val useCase: GetNewsByQueryUseCase
) : ViewModel() {



}