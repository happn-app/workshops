package com.happn.android101.presentation.utils

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

fun Modifier.holo(
    enabled: Boolean,
    shape: Shape = RectangleShape,
): Modifier = composed {
    val density = LocalDensity.current

    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    var touchPosition by remember { mutableStateOf(Offset.Unspecified) }

    this
        .pointerInput(Unit) {
            detectDragGestures(
                onDragEnd = {
                    offsetX = 0f
                    offsetY = 0f
                    touchPosition = Offset.Unspecified
                }
            ) { change, dragAmount ->
                change.consume()
                touchPosition = change.position
                offsetX += dragAmount.x
                offsetY += dragAmount.y
            }
        }
        .graphicsLayer {
            rotationX = -(offsetY / this.size.height) * 20
            rotationY = (offsetX / this.size.width) * 20
        }
        .drawWithContent {
            val path = shape.toPath(size, density)
            this.clipPath(path) {
                this@drawWithContent.drawContent()
                //this.drawSurface(brushOffset = Offset(-offsetX, -offsetY))
                //this.drawReflect(brushOffset = Offset(offsetX, offsetY))
                if (touchPosition != Offset.Unspecified) {
                    /*
                    this.drawLight(
                        center = touchPosition,
                        radius = minOf(this.size.width, this.size.height),
                    )
                    */
                }
            }
        }
}

private val rainbowColors = listOf(
    Color.Red,
    Color.Yellow,
    Color.Green,
    Color.Cyan,
    Color.Blue,
    Color.Magenta,
)

private val transparentColors = listOf(
    Color.Transparent,
    Color.Transparent,
    Color.Transparent,
    Color.Transparent,
    Color.Transparent,
    Color.Transparent,
)

fun Shape.toPath(size: Size, density: Density): Path {
    val outline = createOutline(size, androidx.compose.ui.unit.LayoutDirection.Ltr, density)
    return Path().apply {
        when (outline) {
            is androidx.compose.ui.graphics.Outline.Rectangle -> addRect(outline.rect)
            is androidx.compose.ui.graphics.Outline.Rounded -> addRoundRect(outline.roundRect)
            is androidx.compose.ui.graphics.Outline.Generic -> addPath(outline.path)
        }
    }
}

private fun DrawScope.drawSurface(
    brushOffset: Offset,
) {
    this.drawRect(
        brush = Brush.linearGradient(
            start = brushOffset,
            end = brushOffset.plus(Offset(this.size.width, this.size.height)),
            colors = transparentColors + rainbowColors + transparentColors,

            ),
        alpha = 0.3f,
        blendMode = BlendMode.Overlay,
    )
}

private fun DrawScope.drawReflect(
    brushOffset: Offset,
) {
    this.drawRect(
        brush = Brush.linearGradient(
            start = brushOffset,
            end = brushOffset.plus(Offset(this.size.width, this.size.height)),
            colors = rainbowColors + transparentColors + rainbowColors,
            tileMode = TileMode.Repeated
        ),
        alpha = 0.3f,
        blendMode = BlendMode.Overlay,
    )
}

private fun DrawScope.drawLight(
    center: Offset,
    radius: Float
) {
    this.drawCircle(
        radius = radius,
        center = center,
        brush = Brush.radialGradient(
            center = center,
            colors = listOf(
                Color.White,
                Color.Transparent,
            ),
        ),
        blendMode = BlendMode.Lighten,
        alpha = 0.6f,
    )
}

@Preview
@Composable
private fun SurfacePreview() {
    Canvas(
        modifier = Modifier
            .size(100.dp, 200.dp)
    ) {
        drawSurface(
            brushOffset = Offset.Zero,
        )
    }
}


@Preview
@Composable
fun ReflectPreview() {
    Canvas(
        modifier = Modifier
            .size(100.dp, 200.dp)
    ) {
        drawReflect(
            brushOffset = Offset.Zero,
        )
    }
}

@Preview
@Composable
fun LightPreview() {
    Canvas(
        modifier = Modifier
            .size(100.dp, 200.dp)
            .background(Color.Blue)
    ) {
        drawLight(
            center = this.center,
            radius = minOf(this.size.height, this.size.width),
        )
    }
}