package com.example.newsapp.bookmark.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.common.ui.NewsList
import com.example.newsapp.common.viewmodel.SharedNewsViewModel
import com.example.newsapp.home.HomeViewModel

@Composable
fun BookmarkScreen(
    sharedNewsViewModel: SharedNewsViewModel,
    viewModel: HomeViewModel = hiltViewModel(),
    navDetails: () -> Unit
) {
    val newsBookmark = viewModel.bookmarkedNews.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {

        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.padding(16.dp))

        NewsList(
            modifier = Modifier.weight(1f),
            articles = newsBookmark,
            onCardClick = {
                sharedNewsViewModel.selectNews(it)
                navDetails()
            }
        )
    }
}
