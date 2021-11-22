package com.zj.chart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.zj.chart.line.*
import kotlin.math.min
import kotlin.math.roundToInt

@Composable
fun LineChart(lineDataValues: List<LineChartData>, maxValue: Int, minValue: Int) {
    val offset = 80f
    var scale by remember { mutableStateOf(1f) }
    var canvasOffset by remember { mutableStateOf(Offset.Zero) }
    var clickOffset by remember { mutableStateOf(Offset.Zero) }
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .scale(scale)
            .offset {
                IntOffset(canvasOffset.x.roundToInt(), canvasOffset.y.roundToInt())
            }
            .pointerInput(Unit) {
                // 用于旋转、平移和缩放的手势检测器。一旦达到触摸倾斜，用户就可以使用旋转、平移和缩放手势
                detectTransformGestures { _, pan, zoom, _ ->
                    if (scale * zoom > 1 && scale * zoom < 2.0f) {
                        canvasOffset += pan
                        scale *= zoom
                    }
                }
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        if (scale * 1.2f > 1 && scale * 1.2f < 2.0f) {
                            scale *= 1.2f
                        }
                    },
                    onTap = {
                        clickOffset = it
                    }
                )
            }
    ) {
        val lineSize = min(size.width, size.height) - offset
        val oneSize = lineSize / (maxValue - minValue)
        val num = lineSize / (lineDataValues.size + 1)
        val offsetList = buildPoints(lineDataValues, num, lineSize, oneSize, maxValue, minValue)
        lineDrawAxis(offset, lineSize)
        lineDrawPoints(offsetList, clickOffset)
        lineDrawText(lineDataValues, offsetList, lineSize, offset, minValue, maxValue)
    }
}