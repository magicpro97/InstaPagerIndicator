package com.example.myapplication.ui

import android.content.Context
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import coil.ImageLoader
import coil.imageLoader
import coil.request.ImageRequest
import kotlin.math.ceil
import kotlin.math.min
import kotlin.random.Random

enum class ChartType {
    QUAD, LINE;
}

data class Point(
    val x: Float = 0f,
    val y: Float = 0f,
    val image: String? = null,
)

@Composable
fun CCLineChart(
    modifier: Modifier = Modifier,
    data: List<Point> = emptyList(),
    itemCountX: Int = 4,
    itemCountY: Int = 5,
    textSpacingStartX: Float = 30f,
    lineSpacing: Float = 100f,
    spacingBottom: Float = 50f,
    textSpacingEndX: Float = 50f,
    graphColor: Color = Color.Red,
    lineColor: Color = Color.Red,
    type: ChartType = ChartType.LINE,
    xAxisName: String = "KM",
    yAxisName: String = "M",
    context: Context = LocalContext.current,
    imageLoader: ImageLoader = context.imageLoader,
) {
    var drawables by remember {
        mutableStateOf(emptyList<Drawable>())
    }
    val imagePoints by remember {
        mutableStateOf(data.filter { it.image != null })
    }
    LaunchedEffect(imagePoints) {
        val requests = imagePoints.map { point ->
            ImageRequest
                .Builder(context)
                .data(point.image)
                .build()
        }
        for (request in requests) {
            val result = imageLoader.execute(request)
            result.drawable?.let { drawable ->
                drawables = drawables + drawable
            }
        }
    }

    val transparentGraphColor = remember { graphColor.copy(alpha = 0.5f) }
    val maxYValue = remember { (data.maxOfOrNull { it.y }) ?: 0f }
    val density = LocalDensity.current

    val textPaint = remember(density) {
        Paint().apply {
            color = android.graphics.Color.BLACK
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }

    val linePaint = Paint().apply {
        color = lineColor.toArgb()
        strokeWidth = 1.dp.value
    }

    Canvas(modifier = modifier) {
//        val spacePerXAxis = size.width / (data.size + 2) // + 2 for KM
//        val stepX = ceil(data.size / itemCountX.toFloat()).toInt()
//        var indexX = 1
//        // draw X axis
//        repeat(itemCountX) {
//            if (indexX > data.size - 1) {
//                indexX = data.size - 1
//            }
//            val xValue = data[indexX].x
//            drawContext.canvas.nativeCanvas.apply {
//                drawText(
//                    xValue.toString(),
//                    indexX * spacePerXAxis + textSpacingStartX, // draw from left to right + 30f left
//                    size.height - 10, // draw from bottom is size height
//                    textPaint
//                )
//            }
//            indexX += stepX
//        }
//
//        // Draw KM
//        val minSpace = min((data.size + 1) * spacePerXAxis, size.width - textSpacingEndX)
//        drawContext.canvas.nativeCanvas.apply {
//            drawText(
//                xAxisName,
//                minSpace, // draw from left to right
//                size.height - 10, // draw from bottom is size height
//                textPaint
//            )
//        }
//
//        val spacePerYAxis = size.height / (itemCountY + 2)  // +2 for M and top space
//        val stepY = ceil(maxYValue / (itemCountY - 1).toDouble()).toInt()
//        var yAxisValue = stepY
//
//        var medX = 0f
//        var medY = 0f
//        val spacePerDistance = size.width / data.size
//        val height = size.height - spacingBottom
//        val maxYAxisValue = (itemCountY + 1) * stepY
//        val strokePath = Path().apply {
//            data.indices.forEach { i ->
//                val nextInfo = data.getOrNull(i + 1) ?: data.last()
//
//                val x1 = i * spacePerDistance
//                val y1 = height - (height * data[i].y / maxYAxisValue)
//                val x2 = (i + 1) * spacePerDistance
//                val y2 = height - (height * nextInfo.y / maxYAxisValue)
//                if (i == 0) {
//                    moveTo(x1, y1)
//                } else {
//                    when (type) {
//                        ChartType.QUAD -> {
//                            medX = (x1 + x2) / 2f
//                            medY = (y1 + y2) / 2f
//                            quadraticBezierTo(x1 = x1, y1 = y1, x2 = medX, y2 = medY)
//                        }
//                        ChartType.LINE -> {
//                            lineTo(x = x2, y = y2)
//                        }
//                    }
//                }
//            }
//            // stretch to the end of width
////            quadraticBezierTo(x1 = medX, y1 = medY, x2 = size.width, y2 = medY)
//            when (type) {
//                ChartType.QUAD -> {
//                    quadraticBezierTo(
//                        x1 = medX,
//                        y1 = medY,
//                        x2 = size.width,
//                        y2 = medY + Random.nextInt(10)
//                    )
//                }
//                ChartType.LINE -> {
//                    val y2 = height - (height * data.last().y / maxYAxisValue)
//                    lineTo(x = size.width, y = y2 + Random.nextInt(10))
//                }
//            }
//        }
//
//        drawPath(
//            path = strokePath,
//            color = lineColor,
//            style = Stroke(
//                width = 1.dp.toPx(),
//                cap = StrokeCap.Round
//            )
//        )
//
//        val fillPath = android.graphics.Path(strokePath.asAndroidPath()).asComposePath().apply {
//            lineTo(size.width, size.height - spacingBottom)
//            lineTo(0f, size.height - spacingBottom)
//            close()
//        }
//
//        drawPath(
//            path = fillPath,
//            color = transparentGraphColor
////            brush = Brush.verticalGradient(
////                colors = listOf(
////                    transparentGraphColor,
////                    Color.Transparent
////                ),
////                endY = size.height
////            )
//        )
//
//        // draw Y and guide line
//        for (i in 1..itemCountY) {
//            drawContext.canvas.nativeCanvas.apply {
//                // draw Y axis digits
//                drawText(
//                    yAxisValue.toString(),
//                    textSpacingStartX, // spacing 30 from left
//                    size.height - i * spacePerYAxis - spacingBottom, // 300 - xxx draw from bottom to top - 50f bottom
//                    textPaint
//                )
//                // draw Y axis guide line
//                drawLine(
//                    lineSpacing,
//                    size.height - i * spacePerYAxis - 10 - spacingBottom,
//                    size.width,
//                    size.height - i * spacePerYAxis - 10 - spacingBottom,
//                    linePaint,
//                )
//                yAxisValue += stepY
//            }
//        }
//
//        // draw M and guide line
//        drawContext.canvas.nativeCanvas.apply {
//            // draw M text
//            drawText(
//                yAxisName,
//                textSpacingStartX,
//                size.height - (itemCountY + 1) * spacePerYAxis - spacingBottom,
//                textPaint
//            )
//
//            // draw M guide line
//            drawLine(
//                lineSpacing,
//                size.height - (itemCountY + 1) * spacePerYAxis - 10 - spacingBottom,
//                size.width,
//                size.height - (itemCountY + 1) * spacePerYAxis - 10 - spacingBottom,
//                linePaint,
//            )
//
//            // draw Bottom guide line
//            drawLine(
//                -lineSpacing,
//                size.height - spacingBottom,
//                size.width,
//                size.height - spacingBottom,
//                linePaint,
//            )
//        }
        val last = data.last()
        val ratio = last.x / size.width
        drawables.forEachIndexed { index, drawable ->
            val point = imagePoints[index]
            val pointIndex = data.indexOf(point)
            drawIntoCanvas { canvas ->
                val x = (pointIndex * ratio * point.x).toInt()
                val y = (size.height - point.y - spacingBottom - 50f).toInt()
                val imageSize = 100.dp.value.toInt()
                drawable.bounds = Rect(
                    x,
                    y,
                    x + imageSize,
                    y + imageSize,
                )
                drawable.draw(canvas.nativeCanvas)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewQuadLineChart1() {
    val size = 100
    val randomList = List(size) {
        Point(
            x = it.toFloat(),
            y = Random.nextInt(50).toFloat()
        )
    }

    CCLineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        data = randomList,
        itemCountX = 5,
        itemCountY = 6,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewQuadLineChart2() {
    CCLineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        data = listOf(
            Point(10f, 7f, image = "https://picsum.photos/100/100"),
            Point(20f, 10f),
            Point(30f, 20f),
            Point(40f, 14f, image = "https://picsum.photos/100/100"),
            Point(50f, 24f),
            Point(60f, 17f),
            Point(70f, 10f, image = "https://picsum.photos/100/100"),
            Point(80f, 13f),
            Point(90f, 7f, image = "https://picsum.photos/100/100"),
            Point(100f, 3f),
        ),
        itemCountX = 4,
        itemCountY = 5,
    )
}
