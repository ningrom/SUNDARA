package com.dicoding.sundara.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.sundara.data.remote.response.ArticlesItem
import com.dicoding.sundara.data.remote.response.Source
import com.dicoding.sundara.ui.theme.SundaraTheme

@Composable
fun ArticleList(
    articles: List<ArticlesItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(articles) { article ->
            ArticleItem(
                article = article,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
fun ArticleItem(
    article: ArticlesItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(com.dicoding.sundara.R.drawable.translate),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = article.source?.name.orEmpty(),
            style = typography.titleMedium,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Terbit: ${article.publishedAt.orEmpty()}",
            style = typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun ArticleListPreview() {
    SundaraTheme {
        val dummyArticles = List(5) {
            ArticlesItem(
                title = "Dummy Title $it",
                source = Source(name = "Dummy Source"),
                publishedAt = "2023-01-01T00:00:00Z"
            )
        }
        ArticleList(articles = dummyArticles)
    }
}