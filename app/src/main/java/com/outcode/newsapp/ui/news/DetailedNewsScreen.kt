package com.outcode.newsapp.ui.news

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.outcode.newsapp.R
import com.outcode.newsapp.data.response.Article
import com.outcode.newsapp.data.response.Source
import com.outcode.newsapp.style.articleTitleStyle
import com.outcode.newsapp.style.dateTextStyle
import com.outcode.newsapp.style.sourceTextStyle
import com.outcode.newsapp.ui.commoncomposable.HeightSpacer
import com.outcode.newsapp.ui.commoncomposable.RemoteImage
import com.outcode.newsapp.ui.commoncomposable.TopAppBarWithBack
import com.outcode.newsapp.utils.getDateWithServerTimeStamp


/**
 * Created by Ayush Shrestha$ on 2022/12/13$.
 */

@Composable
fun DetailedNewsScreen(navHostController: NavHostController,viewModel: NewsViewModel) {
   //  val  article: Article = Article("","","","", Source("",""),"","","")

   val article = viewModel.newsDetail?:return
    Surface(
    ) {
        Column() {
            TopAppBarWithBack(titleResource = R.string.app_name) {
                navHostController.popBackStack()
            }
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)) {

                Text(
                    text = article.title,
                    style = articleTitleStyle.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    ),
                )
                HeightSpacer(value = 12.dp)
                Row {
                    Text(
                        maxLines = 1,
                        text = "By ${article.author + "on Updated "}",
                        style = dateTextStyle.copy(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )

                    )
                    article.publishedAt.getDateWithServerTimeStamp()?.let {
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
                HeightSpacer(value = 12.dp)

                RemoteImage(
                    url = article.urlToImage,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                HeightSpacer(value = 12.dp)
                Text(
                    text = article.content,
                    style = sourceTextStyle.copy(color = Color.Black),
                    overflow = TextOverflow.Ellipsis
                )
            }

        }


    }
}