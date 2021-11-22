package com.zj.chart.line

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope

fun DrawScope.lineDrawAxis(
    offset: Float,
    lineSize: Float
) {
    // x轴
    drawLine(
        color = Color.Green,
        start = Offset(offset, lineSize),
        end = Offset(lineSize - offset, lineSize),
        cap = StrokeCap.Round,
        strokeWidth = 10f,
    )
    // x轴上的箭头
    lineDrawXArrow(offset, lineSize, lineSize - offset + 30, lineSize)

    // y轴
    drawLine(
        color = Color.Green, start = Offset(offset, offset), end = Offset(offset, lineSize),
        cap = StrokeCap.Round,
        strokeWidth = 10f,
    )
    // y轴上的箭头
    lineDrawYArrow(offset, offset - 30, offset, lineSize)
}