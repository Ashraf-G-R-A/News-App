package com.example.newsapp.home.view

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun NewsBottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = { onItemClick(index) },
                icon = {
                    Column(
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.text,
                            tint = if (selectedItem == index) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onBackground
                            }

                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = item.text,
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.background,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                    unselectedTextColor = MaterialTheme.colorScheme.onBackground

                )
            )

        }
    }

}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NewsBottomNavigationPreview() {
    NewsAppTheme {
        NewsBottomNavigation(
            items = listOf(
                BottomNavigationItem(
                    icon = com.example.newsapp.R.drawable.ic_home,
                    text = "Home"
                ),
                BottomNavigationItem(
                    icon = com.example.newsapp.R.drawable.ic_search,
                    text = "Search"
                ),
                BottomNavigationItem(
                    icon = com.example.newsapp.R.drawable.ic_bookmark,
                    text = "Bookmark"
                ),
            ),
            selectedItem = 2,
            onItemClick = {}
        )
    }
}