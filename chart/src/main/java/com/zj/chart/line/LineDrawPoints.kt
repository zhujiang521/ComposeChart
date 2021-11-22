package com.zj.chart.line

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.abs

fun DrawScope.lineDrawPoints(offsetList: ArrayList<Offset>, clickOffset: Offset) {
    drawPoints(
        offsetList,
        PointMode.Polygon,
        Color.Blue,
        strokeWidth = 15f,
        cap = StrokeCap.Round
    )
    offsetList.forEach { point ->
        if (detectOffset(clickOffset, point)) {
            drawPoints(
                arrayListOf(point),
                PointMode.Points,
                Color.Green,
                strokeWidth = 35f,
                cap = StrokeCap.Round
            )
        } else {
            drawPoints(
                arrayListOf(point),
                PointMode.Points,
                Color.Red,
                strokeWidth = 25f,
                cap = StrokeCap.Round
            )
        }
    }
}

/**
 * 检测点击的位置是否有需要响应的点
 */
fun detectOffset(offset1: Offset, offset2: Offset): Boolean {
    return abs(offset2.x - offset1.x) < 50 && abs(offset2.y - offset1.y) < 50
}