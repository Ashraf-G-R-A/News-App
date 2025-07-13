package com.example.newsapp.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.common.ui.NewsList
import com.example.newsapp.common.ui.SearchBar
import com.example.newsapp.home.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navSearch: () -> Unit,
    navDetails: () -> Unit,
) {
    val articles = viewModel.news.collectAsLazyPagingItems()
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 0) {
                articles.itemSnapshotList.items.map { it.title }
            } else {
                listOf()
            }
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        SearchBar(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "",
            readOnly = true,
            onSearch = {},
            onValueChange = {},
            onClick = {
                navSearch()
            })

        Text(
            text = titles.joinToString(", "),
            modifier = Modifier
                .padding(16.dp)
                .basicMarquee(),
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.background
        )

        Spacer(modifier = Modifier.height(16.dp))

        NewsList(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f),
            articles = articles,
            onCardClick = {
                navDetails()
            },
        )


    }
}