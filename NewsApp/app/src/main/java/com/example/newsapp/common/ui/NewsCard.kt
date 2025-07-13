package com.example.newsapp.common.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.domain.news.model.NewsEntity
import com.example.newsapp.R
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun NewsCard(modifier: Modifier = Modifier, article: NewsEntity, onClick: () -> Unit) {
    val context = LocalContext.current

    Row(modifier = modifier.clickable { onClick() }) {
        Box(
            modifier = Modifier
                .padding(top = 6.dp)
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(article.urlToImage)
                    .placeholder(R.drawable.ic_logo)
                    .error(R.drawable.ic_logo)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }



        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(start = 8.dp)
                .height(96.dp)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                color = MaterialTheme.colorScheme.onBackground,
                overflow = TextOverflow.Ellipsis,

                )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.sourceName,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    painter = androidx.compose.ui.res.painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(8.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                )

            }

        }

    }

}

val mockNewsList = listOf(
    NewsEntity(
        title = "AI is transforming medicine",
        description = "Doctors are using AI to diagnose diseases faster and more accurately than ever before.",
        url = "https://example.com/ai-medicine",
        urlToImage = "https://example.com/images/ai.jpg",
        publishedAt = "2025-07-11T14:15:00Z",
        author = "Jane Smith",
        sourceName = "CNN"
    ),
    NewsEntity(
        title = "Global markets rally after US jobs report",
        description = "Stocks surged worldwide as investors cheered a stronger-than-expected US employment report.",
        url = "https://example.com/markets-rally",
        urlToImage = "https://example.com/images/markets.jpg",
        publishedAt = "2025-07-10T10:45:00Z",
        author = "Ali Hassan",
        sourceName = "Reuters"
    )
)

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun NewsCardPreview() {
    NewsAppTheme {
        NewsCard(
            article = mockNewsList[0],
            onClick = {}
        )
    }
}

