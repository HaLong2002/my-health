package com.example.myhealth.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myhealth.core.designsystem.R
import com.example.myhealth.core.designsystem.theme.MyHealthTheme

@Composable
fun MyHealthImage(
    modifier: Modifier = Modifier,
    imageRes: Int,
    shape: Shape = CircleShape,
    elevation: Dp = 0.dp,
) {
    Box(
        modifier = modifier
            .shadow(elevation = elevation, shape = shape)
            .clip(shape)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun MyHealthImagePreview() {
    MyHealthTheme {
        MyHealthImage(
            modifier = Modifier.size(100.dp),
            imageRes = R.drawable.cupcake
        )
    }
}