package com.example.newsapp.search.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.common.search.SearchEvent
import com.example.newsapp.common.ui.NewsList
import com.example.newsapp.common.ui.SearchBar
import com.example.newsapp.search.SearchViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navDetails: () -> Unit
) {
    val event = viewModel::onEvent
    val state = viewModel.state


    Column(
        modifier = Modifier
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
            .statusBarsPadding()
    ) {
        SearchBar(
            text = state.query,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateQuery(it)) },
            onSearch = { }
        )

        Spacer(modifier = Modifier.padding(16.dp))

        state.articles?.let {
            NewsList(
                articles = it.collectAsLazyPagingItems(),
                onCardClick = {
                    navDetails()
                }
            )
        }
    }


}