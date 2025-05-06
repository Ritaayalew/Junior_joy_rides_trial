package com.example.trial_junior.feature_junior.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BarGraph(modifier: Modifier = Modifier, data: List<Int>) {
    val textMeasurer = rememberTextMeasurer()
    val density = LocalDensity.current
    val dayLabels = listOf("M", "T", "W", "T", "F", "S", "S") // Adjust if your data list size is different

    Card(
        modifier = modifier.padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Weekly Activities Report", fontSize = 18.sp)
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                val paddingFromAxes = 20.dp.toPx() // Padding from the edges for axes
                val barWidth = (size.width - 2 * paddingFromAxes) / (data.size * 2)
                val maxVal = data.maxOrNull() ?: 1
                val graphHeight = size.height - 2 * paddingFromAxes // Height available for the graph itself
                val scaleFactor = graphHeight / maxVal

                // Define the origin of the graph (bottom-left corner of the drawable area)
                val originX = paddingFromAxes
                val originY = size.height - paddingFromAxes

                // Draw x-axis
                drawLine(
                    color = Color.Black,
                    start = Offset(originX, originY),
                    end = Offset(size.width - paddingFromAxes, originY),
                    strokeWidth = 2f
                )

                // Draw y-axis
                drawLine(
                    color = Color.Black,
                    start = Offset(originX, originY),
                    end = Offset(originX, paddingFromAxes),
                    strokeWidth = 2f
                )

                // Draw bars
                data.forEachIndexed { index, value ->
                    val barLeft = originX + (barWidth * 2 * index) + barWidth / 2 // Center the bar over the x-axis tick
                    val barTop = originY - (value * scaleFactor)
                    val barRight = barLeft + barWidth

                    drawRect(
                        color = Color(0xFF344BFD),
                        topLeft = Offset(barLeft, barTop),
                        size = Size(barWidth, value * scaleFactor),

                        )

                    // Draw x-axis labels (under each bar)
                    // Use the day label for the current index
                    val xLabel = dayLabels.getOrElse(index) { "" } // Get label, default to empty if index out of bounds
                    val textLayoutResultX = textMeasurer.measure(
                        text = xLabel,
                        style = TextStyle(fontSize = 12.sp, textAlign = TextAlign.Center)
                    )
                    val xLabelX = barLeft + barWidth / 2 - textLayoutResultX.size.width / 2
                    val xLabelY = originY + 16.dp.toPx() // Position below the x-axis

                    drawIntoCanvas {
                        it.nativeCanvas.drawText(
                            xLabel,
                            xLabelX,
                            xLabelY,
                            Paint().asFrameworkPaint().apply {
                                color = android.graphics.Color.BLACK
                                textSize = with(density) { 12.sp.toPx() }
                                textAlign = android.graphics.Paint.Align.LEFT // Align left for drawing text
                            }
                        )
                    }
                }

                // Draw y-axis labels
                val yAxisLabelCount = 5 // Number of labels on the y-axis
                val yAxisStep = maxVal / yAxisLabelCount
                for (i in 0..yAxisLabelCount) {
                    val yValue = i * yAxisStep
                    val yLabel = yValue.toString()
                    val yPosition = originY - (yValue * scaleFactor)

                    val textLayoutResultY = textMeasurer.measure(
                        text = yLabel,
                        style = TextStyle(fontSize = 12.sp, textAlign = TextAlign.Right)
                    )
                    val yLabelX = originX - 5.dp.toPx() - textLayoutResultY.size.width // Position to the left of the y-axis
                    val yLabelY = yPosition + textLayoutResultY.size.height / 2 // Vertically center the label

                    drawIntoCanvas {
                        it.nativeCanvas.drawText(
                            yLabel,
                            yLabelX,
                            yLabelY,
                            Paint().asFrameworkPaint().apply {
                                color = android.graphics.Color.BLACK
                                textSize = with(density) { 12.sp.toPx() }
                                textAlign = android.graphics.Paint.Align.LEFT // Align left for drawing text
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BarGraphScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Bar Graph", fontSize = 24.sp)
        BarGraph(data = listOf(10, 30, 50, 20, 40))
    }
}