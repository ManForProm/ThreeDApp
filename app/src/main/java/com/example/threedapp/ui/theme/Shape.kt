package com.example.threedapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)
val RoundedShapes = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(7.dp),
    large = RoundedCornerShape(5.dp),
)

@Preview
@Composable
fun ShapePreview() {

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {
        val shape = GenericShape { size: Size, layoutDirection: LayoutDirection ->
            val width = size.width
            val height = size.height

            addArc(
                oval = Rect(
                    offset = Offset.Zero,
                    size = Size(height, height)
                ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = 180f,
            )
            lineTo(width, 0f)
            lineTo(width, height)
            lineTo(height, height)

        }

        Box(
            modifier = Modifier
                .size(200.dp, 100.dp)
                .clip(shape)
                .drawWithContent {


                    with(drawContext.canvas.nativeCanvas) {
                        val checkPoint = saveLayer(null, null)
                        // Destination
                        drawContent()

                        restoreToCount(checkPoint)
                    }
                }
                .background(Color.Magenta)
                .clickable {

                }
        )
    }
}