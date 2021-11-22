package com.zj.chart.line

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.sqrt


fun DrawScope.lineDrawYArrow(
    fromX: Float, fromY: Float, toX: Float, toY: Float,
) {

    val height = 30
    val bottom = 15
    val juli = sqrt(
        ((toX - fromX) * (toX - fromX)
                + (toY - fromY) * (toY - fromY)).toDouble()
    ).toFloat() // 获取线段距离
    val juliX = toX - fromX // 有正负，不要取绝对值
    val juliY = toY - fromY // 有正负，不要取绝对值
    val dian2X = fromX + height / juli * juliX
    val dian2Y = fromY + height / juli * juliY
    //起点的箭头
    val path2 = Path()
    path2.moveTo(fromX, fromY) // 此点为边形的起点
    path2.lineTo(
        dian2X + bottom / juli * juliY, dian2Y
                - bottom / juli * juliX
    )
    path2.lineTo(
        dian2X - bottom / juli * juliY, dian2Y
                + bottom / juli * juliX
    )
    path2.close() // 使这些点构成封闭的三边形
    drawPath(path2, Color.Red)
}

fun DrawScope.lineDrawXArrow(
    fromX: Float, fromY: Float, toX: Float, toY: Float,
) {

    val height = 30
    val bottom = 15
    val juli = sqrt(
        ((toX - fromX) * (toX - fromX)
                + (toY - fromY) * (toY - fromY)).toDouble()
    ).toFloat() // 获取线段距离
    val juliX = toX - fromX // 有正负，不要取绝对值
    val juliY = toY - fromY // 有正负，不要取绝对值
    val dianX = toX - height / juli * juliX
    val dianY = toY - height / juli * juliY

    //终点的箭头
    val path = Path()
    path.moveTo(toX, toY) // 此点为三边形的起点
    path.lineTo(
        dianX + bottom / juli * juliY, dianY
                - bottom / juli * juliX
    )
    path.lineTo(
        dianX - bottom / juli * juliY, dianY
                + bottom / juli * juliX
    )
    path.close() // 使这些点构成封闭的三边形
    drawPath(path, Color.Red)
}