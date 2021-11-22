package com.zj.chart.line

import androidx.compose.ui.geometry.Offset

/**
 * 构建点的List
 *
 * @param lineDataValues 数据的List
 * @param num 数量
 * @param lineSize 折线图的size
 * @param oneSize 每1个数量代表的值
 * @param maxValue 折线图展示的最大值
 * @param minValue 折线图展示的最小值
 */
fun buildPoints(
    lineDataValues: List<LineChartData>,
    num: Float,
    lineSize: Float,
    oneSize: Float,
    maxValue: Int,
    minValue: Int
): ArrayList<Offset> {
    val offsetList = arrayListOf<Offset>()
    for (index in lineDataValues.indices) {
        val y = when {
            lineDataValues[index].num > maxValue -> {
                maxValue.toFloat()
            }
            lineDataValues[index].num < minValue -> {
                minValue.toFloat()
            }
            else -> {
                lineDataValues[index].num
            }
        }

        offsetList.add(
            Offset(
                x = (index + 1) * num,
                y = lineSize - y * oneSize
            )
        )
    }
    return offsetList
}