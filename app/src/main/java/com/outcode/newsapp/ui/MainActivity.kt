package com.outcode.newsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.outcode.newsapp.style.articleTitleStyle
import com.outcode.newsapp.style.dateTextStyle
import com.outcode.newsapp.style.sourceTextStyle
import com.outcode.newsapp.R
import com.outcode.newsapp.data.response.Article
import com.outcode.newsapp.ui.commoncomposable.*
import com.outcode.newsapp.ui.news.NewsViewModel
import com.outcode.newsapp.utils.getDateWithServerTimeStamp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Column {
                TopAppBar(R.string.app_name, onThemeSwitch = {
                   // onThemeSwitch()
                })
                NewsListUi(viewModel)

            }
        }
    }
}

@Composable
fun NewsListUi(viewModel: NewsViewModel) {
    val uiState = viewModel.activeNewsUiState.observeAsState().value
    uiState ?: return
    Surface(
        color = MaterialTheme.colors.background,
        shape = RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 10.dp,
                end = 10.dp,
                bottom = 10.dp
            )
    ) {

        when {
            uiState.isLoading -> {
                CircularLoader()
            }
            uiState.error != null -> {
                ErrorView(
                    errorMessage = uiState.error.errorMessage,
                    showRetry = uiState.error.showRetry,
                    // retry = retry
                )
            }
            uiState.list?.isEmpty() == false -> {
                LazyColumn() {
                    itemsIndexed(uiState.list) { index, item ->
                        NewsList(item) {
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun NewsList(data: Article, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.White)
            .clickable {
                onClick()
            }
    ) {
        Box(
            modifier = Modifier
                .padding(5.dp)
        ) {
            Column(modifier = Modifier.clickable(onClick = { })) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RemoteImage(
                        url = data.urlToImage,
                        modifier = Modifier.size(90.dp)
                    )
                    WidthSpacer(value = 10.dp)
                    Column {
                        Text(
                            text = data.title,
                            style = articleTitleStyle.copy(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            ),
                            maxLines = 2
                        )
                        HeightSpacer(value = 4.dp)
                        Text(
                            text = data.description,
                            style = sourceTextStyle.copy(color = Color.Black),
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                        HeightSpacer(value = 4.dp)
                        data.publishedAt.getDateWithServerTimeStamp()?.let {
                            Text(
                                maxLines = 1,
                                text = it,
                                style = dateTextStyle.copy(
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )

                            )
                        }
                    }
                }
                HeightSpacer(value = 10.dp)
            }
        }

    }
}
