package com.example.newsapp.detailsnews

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.R
import com.example.newsapp.common.viewmodel.SharedNewsViewModel
import com.example.newsapp.home.HomeViewModel
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun DetailsScreen(
    sharedNewsViewModel: SharedNewsViewModel,
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    val context = LocalContext.current

    val news by sharedNewsViewModel.selectedNews.observeAsState()



    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBookmarkClick = {
                news?.let { homeViewModel.addNews(it) }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, news?.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }

                }
            },
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(news?.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBackClick = {
                navigateUp()
            }
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(news?.urlToImage)
                        .placeholder(
                            R.drawable.ic_logo
                        )
                        .error(R.drawable.ic_logo).build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(248.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = news?.title.toString(),
                    style = androidx.compose.material3.MaterialTheme.typography.displaySmall,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = news?.description.toString(),
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.secondary
                )

            }

        }


    }

}


@Preview(showBackground = true)
@Composable
fun DetailsPreview() {
    NewsAppTheme {

    }
}
