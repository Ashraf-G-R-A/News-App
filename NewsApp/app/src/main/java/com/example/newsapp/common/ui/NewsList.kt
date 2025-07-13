package com.example.newsapp.common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.core.resut.Result
import com.example.domain.news.model.NewsEntity

@Composable
fun NewsList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<NewsEntity>,
    onCardClick: (NewsEntity) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        handlePagingResult(articles).fold(
            onSuccess = { list ->
                list.takeIf { it.isNotEmpty() }?.let {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(all = 8.dp)
                    ) {
                        items(it) { news ->
                            NewsCard(
                                article = news,
                                onClick = { onCardClick(news) }
                            )
                        }
                    }
                } ?: EmptyScreen()

            },
            onFailure = { throwable ->
                EmptyScreen(error = Result.Failure(throwable))
            },
            onLoading = {
                ShimmerEffect()
            }
        )
    }

}


fun handlePagingResult(
    articles: LazyPagingItems<NewsEntity>,
): Result<List<NewsEntity>> {
    val loadState = articles.loadState

    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            Result.Loading
        }

        error != null -> {
            Result.Failure(error.error)
        }

        else -> {
            val currentList = articles.itemSnapshotList.items
            Result.Success(currentList)
        }
    }
}


@Composable
private fun ShimmerEffect() {

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        repeat(10) {
            NewsCardShimmerEffect(
                modifier = Modifier.padding(8.dp)
            )
        }
    }

}