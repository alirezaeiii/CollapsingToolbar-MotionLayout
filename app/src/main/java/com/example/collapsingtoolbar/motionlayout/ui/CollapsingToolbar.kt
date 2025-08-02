package com.example.collapsingtoolbar.motionlayout.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.example.collapsingtoolbar.motionlayout.R

@OptIn(ExperimentalMotionApi::class)
@Preview(group = "scroll")
@Composable
fun ToolBarExampleDsl(modifier: Modifier = Modifier) {
    val scroll = rememberScrollState(0)
    val big = 350.dp
    val small = 64.dp
    val scene = MotionScene {
        val (title, image, icon, button) = createRefsFor("title", "image", "icon", "button")

        val start1 = constraintSet {
            constrain(title) {
                bottom.linkTo(image.bottom)
                start.linkTo(image.start)
            }
            constrain(image) {
                width = Dimension.matchParent
                height = Dimension.value(big)
                top.linkTo(parent.top)
                customColor("cover", Color(0x000000FF))
            }
            constrain(icon) {
                top.linkTo(image.top, 16.dp)
                start.linkTo(image.start, 16.dp)
                customColor("bg", Color.White)
                customColor("tint", Color.Black)
                width = Dimension.value(32.dp)
                height = Dimension.value(32.dp)
            }
            constrain(button) {
                top.linkTo(image.bottom, (-23.5).dp)
                centerHorizontallyTo(image)
            }
        }
        val end1 = constraintSet {
            constrain(title) {
                bottom.linkTo(image.bottom)
                start.linkTo(icon.end)
                centerVerticallyTo(image)
                scaleX = 0.7f
                scaleY = 0.7f
            }
            constrain(image) {
                width = Dimension.matchParent
                height = Dimension.value(small)
                top.linkTo(parent.top)
                customColor("cover", Color(0xFF0000FF))
            }
            constrain(icon) {
                top.linkTo(image.top, 8.dp)
                start.linkTo(image.start, 16.dp)
                customColor("bg", Color.Transparent)
                customColor("tint", Color.White)
            }
            constrain(button) {
                top.linkTo(image.bottom, (-8).dp)
                centerHorizontallyTo(image)
            }
        }
        transition(start1, end1, "default") {}
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(scroll)
    ) {
        Spacer(Modifier.height(big))
        repeat(5) {
            Text(
                text = LoremIpsum(222).values.first(),
                modifier = Modifier
                    .background(Color.White)
                    .padding(32.dp),
                color = Color.Black
            )
        }
    }
    val gap = with(LocalDensity.current) { big.toPx() - small.toPx() }
    val progress = minOf(scroll.value / gap, 1f)

    MotionLayout(
        modifier = modifier.fillMaxSize(),
        motionScene = scene,
        progress = progress
    ) {

        val coverColor = customColor("image", "cover")

        Box(
            modifier = Modifier
                .layoutId("image")
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.bridge),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(coverColor)
            )
        }
        IconButton(
            onClick = {},
            modifier = Modifier
                .layoutId("icon")
                .background(color = customColor("icon", "bg"), shape = CircleShape)
        ) {
            Icon(
                Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "back",
                tint = customColor("icon", "tint")
            )
        }
        Text(
            modifier = Modifier
                .layoutId("title")
                .padding(vertical = 40.dp, horizontal = 12.dp),
            text = "San Francisco",
            fontSize = 30.sp,
            color = Color.White
        )
        Button(
            onClick = {},
            shape = RoundedCornerShape(36.dp),
            modifier = Modifier
                .layoutId("button")
                .width(200.dp)
        ) {
            Text(text = "DeepLink")
        }
    }
}