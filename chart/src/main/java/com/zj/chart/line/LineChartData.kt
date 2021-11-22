package com.zj.chart.line

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * 折线图数据类
 */
data class LineChartData(
    // 模块数值
    val num: Float,
    // 模块名称
    val text: String,
    // 模块的颜色
    val color: Color? = null,
    // 渐变颜色
    val brush: Brush? = null,
)