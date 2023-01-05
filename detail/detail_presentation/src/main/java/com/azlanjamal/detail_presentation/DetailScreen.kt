package com.azlanjamal.detail_presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.azlanjamal.core_presentation.value.LocalSpacing
import com.azlanjamal.detail_domain.model.Detail
import com.azlanjamal.detail_presentation.DetailViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.azlanjamal.common.R
import com.azlanjamal.core_presentation.value.Dimensions

@ExperimentalCoilApi
@Composable
fun DetailScreen(
    businessId: String? = null,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.detail?.let { detail ->
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                val businessLatLng =
                    LatLng(detail.coordinates.latitude, detail.coordinates.longitude)
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(businessLatLng, 15f)
                }
                Header(detail)

                Spacer(modifier = Modifier.height(spacing.spaceLarge))

                Heading(title = stringResource(id = R.string.location), spacing = spacing)

                Spacer(modifier = Modifier.height(spacing.spaceSmall))

                GoogleMap(
                    modifier = Modifier.fillMaxHeight(0.35f),
                    cameraPositionState = cameraPositionState
                ) {
                    Marker(
                        position = businessLatLng,
                        title = detail.name,
                    )
                }

                Spacer(modifier = Modifier.height(spacing.spaceLarge))

                Heading(title = stringResource(id = R.string.business_info), spacing = spacing)

                Spacer(modifier = Modifier.height(spacing.spaceSmall))

                BusinessInfo(detail = detail, spacing = spacing)
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun Heading(
    title: String,
    spacing: Dimensions
) {
    Text(
        text = title,
        style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.Medium,
        color = Color.Black,
        fontSize = 22.sp,
        modifier = Modifier.padding(start = spacing.spaceMedium)
    )
}

@Composable
fun BusinessInfo(
    detail: Detail,
    spacing: Dimensions
) {
    val displayAddress = detail.location.displayAddress.toString()
    val address = displayAddress.substring(1, displayAddress.length - 1)
    Text(
        text = address,
        fontSize = 14.sp,
        modifier = Modifier.padding(start = spacing.spaceMedium)
    )
    Spacer(modifier = Modifier.height(spacing.spaceSmall))
    Text(
        text = "Phone: " + detail.phone,
        fontSize = 14.sp,
        modifier = Modifier.padding(start = spacing.spaceMedium)
    )
}

@ExperimentalCoilApi
@Composable
fun Header(
    detail: Detail
) {
    val spacing = LocalSpacing.current
    val cardModifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.35f) // 25% area fill
        .background(color = Color.Transparent)
    Card(
        modifier = cardModifier
    ) {
        Image(
            painter = rememberImagePainter(
                data = detail.imageUrl,
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = "business-image",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        0F to Color.Transparent,
                        .5F to Color.Black.copy(alpha = 0.5F),
                        1F to Color.Black.copy(alpha = 0.8F)
                    )
                )
                .padding(start = spacing.spaceMedium, bottom = spacing.spaceMedium),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = detail.name,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 24.sp
            )
            Card(
                backgroundColor = Color.White,
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(top = spacing.spaceSmall),
                shape = RoundedCornerShape(
                    topStart = 15.dp,
                    bottomStart = 15.dp,
                    topEnd = 15.dp,
                    bottomEnd = 15.dp
                )
            ) {
                val isClaimedText = if (detail.isClaimed) {
                    "Claimed"
                } else {
                    "Unclaimed"
                }
                Text(
                    modifier = Modifier.padding(all = spacing.spaceSmall),
                    text = isClaimedText,
                    color = Color.DarkGray,
                    fontSize = 15.sp
                )
            }
        }

    }
}