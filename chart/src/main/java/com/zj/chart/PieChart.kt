package com.zj.chart

import android.graphics.Paint
import android.graphics.Paint.Align
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

const val FDEG2RAD = Math.PI.toFloat() / 180

private const val TAG = "PieChart"

@Composable
fun PieChart(vararg pieDataValues: PieChartData) {
    var startAngle = -90f
    var numbers = 0f
    pieDataValues.forEach { pieData ->
        numbers += pieData.num
        if (pieData.brush == null && pieData.color == null) {
            throw NullPointerException("brush 或 color 必须有一个不能为空")
        }
    }
    val one = 360f / numbers
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        val arcSize = min(size.width, size.height)
        pieDataValues.forEach { data ->
            if (data.brush != null) {
                drawArc(
                    brush = data.brush,
                    startAngle = startAngle,
                    sweepAngle = one * data.num,
                    useCenter = true,
                    size = Size(arcSize, arcSize)
                )
            } else {
                drawArc(
                    color = data.color!!,
                    startAngle = startAngle,
                    sweepAngle = one * data.num,
                    useCenter = true,
                    size = Size(arcSize, arcSize)
                )
            }
            drawIntoCanvas { canvas ->
                val pt2x: Float
                val pt2y: Float
                val labelPtx: Float
                val labelPty: Float
                val sliceXBase =
                    cos(abs(startAngle * FDEG2RAD.toDouble())).toFloat()
                val sliceYBase =
                    sin(abs(startAngle * FDEG2RAD.toDouble())).toFloat()
                val pt1x: Float = startAngle * sliceXBase + center.x
                val pt1y: Float = startAngle * sliceYBase + center.y / 2
                Log.e(TAG, "PieChart: startAngle:$startAngle  sliceXBase:$sliceXBase   sliceYBase:$sliceYBase")
                Log.e(TAG, "PieChart: pt1x:$pt1x   pt1y:$pt1y")
                if (startAngle % 360.0 in 90.0..270.0) {
                    pt2x = pt1x - 10
                    pt2y = pt1y
                    labelPtx = pt2x - 20
                    labelPty = pt2y
                } else {
                    pt2x = pt1x + 10
                    pt2y = pt1y
                    labelPtx = pt2x + 20
                    labelPty = pt2y
                }
                Log.e(TAG, "PieChart: labelPtx:$labelPtx   labelPty:$labelPty")
                val nativeCanvas = canvas.nativeCanvas
                nativeCanvas.drawText(data.text, labelPtx, labelPty, getTextPaint())
            }
            startAngle += one * data.num
        }

    }
}

fun getTextPaint(): Paint {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    paint.textSize = 38f
    paint.textAlign = Paint.Align.CENTER
    return paint
}

/**
 * 饼状图数据类
 */
data class PieChartData(
    // 模块数值
    val num: Float,
    // 模块名称
    val text: String,
    // 模块的颜色
    val color: Color? = null,
    // 渐变颜色
    val brush: Brush? = null,
)