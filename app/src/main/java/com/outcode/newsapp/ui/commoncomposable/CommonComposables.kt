package com.outcode.newsapp.ui.commoncomposable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.outcode.newsapp.style.articleTitleStyle
import com.outcode.newsapp.R
import com.outcode.newsapp.style.titleColorDark

/**
 * Created by Akash on 29/08/20
 */

@Composable
fun HeightSpacer(value: Dp) {
    Spacer(modifier = Modifier.requiredHeight(value))
}

@Composable
fun WidthSpacer(value: Dp) {
    Spacer(modifier = Modifier.requiredWidth(value))
}

@Composable
fun RemoteImage(
    url: String?,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    shape: Shape = RectangleShape
) {
    Box(
        modifier = modifier
    ) {
        if (url.isNullOrEmpty()) {
            Icon(
                painter = painterResource(id = R.drawable.ic_newzz_error),
                contentDescription = "error image",
                tint = if (MaterialTheme.colors.isLight) Color.Black else titleColorDark
            )
        } else {
            Surface(
                color = Color.Transparent,
                shape = shape
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(url)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(shape)
                )
            }
        }
    }
}

@Composable
 fun CircularLoader() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.primary
        )
    }
}


@Composable
fun ErrorView(
    errorMessage: String,
    showRetry: Boolean,
    //retry : () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = errorMessage,
            style = articleTitleStyle.copy(color = MaterialTheme.colors.onSurface)
        )
        if (showRetry) {
            TextButton(onClick = {}) {
                Text(
                    text = stringResource(id = R.string.retry),
                    style = TextStyle(
                        color = MaterialTheme.colors.onSurface
                    )
                )
            }
        }
    }
}