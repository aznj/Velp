package com.azlanjamal.home_presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@ExperimentalCoilApi
@Composable
fun HomeScreen(
    onNavigateToSearch: () -> Unit?
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(onNavigateToSearch)
    }
}

@ExperimentalCoilApi
@Composable
fun Header(
    onNavigateToSearch: () -> Unit?
) {
    val cardShape = RoundedCornerShape(
        bottomStart = 30.dp,
        bottomEnd = 30.dp
    )
    val cardModifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.25f) // 25% area fill
        .background(color = Color.Transparent)
    Card(
        backgroundColor = Color.White,
        shape = cardShape,
        modifier = cardModifier
    ) {
        val headerUrl = "https://images.unsplash.com/photo-1496412705862-e0088f16f791?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80"
        Image(
            painter = rememberImagePainter(
                data = headerUrl,
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = "header",
            contentScale = ContentScale.Crop,
        )
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 40.dp)
        ) {
            SearchBox(onNavigateToSearch)
        }
    }
}

@Composable
fun SearchBox(
    onNavigateToSearch: () -> Unit?
) {
    Card(
        elevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth(0.80f)
            .height(40.dp)
            .background(Color.White)
            .clickable(onClick = { onNavigateToSearch() }),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = "Search for burgers, restaurants"
            )
        }
    }
}