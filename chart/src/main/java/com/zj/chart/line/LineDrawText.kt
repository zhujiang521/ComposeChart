package com.zj.chart.line

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import com.zj.chart.getTextPaint

fun DrawScope.lineDrawText(
    lineDataValues: List<LineChartData>,
    offsetList: ArrayList<Offset>,
    lineSize: Float,
    offset: Float,
    minValue: Int,
    maxValue: Int
) {
    drawIntoCanvas { canvas ->
        val nativeCanvas = canvas.nativeCanvas
        for (index in lineDataValues.indices) {
            nativeCanvas.drawText(
                lineDataValues[index].text,
                offsetList[index].x,
                lineSize + offset,
                getTextPaint()
            )
        }
        nativeCanvas.drawText(
            minValue.toString(),
            offset - 35,
            lineSize,
            getTextPaint()
        )
        nativeCanvas.drawText(
            maxValue.toString(),
            offset - 40,
            offset + 32,
            getTextPaint()
        )
    }
}
