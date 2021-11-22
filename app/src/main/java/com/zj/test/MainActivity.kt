package com.zj.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.zj.chart.LineChart
import com.zj.chart.line.LineChartData
import com.zj.test.ui.theme.ComposeChartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeChartTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                    PieChart(
//                        PieChartData(35f, "测试1", Color.Blue),
//                        PieChartData(25f, "测试2", Color.Yellow),
//                        PieChartData(15f, "测试3", Color.Red),
//                        PieChartData(25f, "测试4", Color.Green),
//                    )
                    val lineCharts = arrayListOf<LineChartData>()
                    lineCharts.add(LineChartData(15f, "测试1", Color.Blue))
                    lineCharts.add(LineChartData(35f, "测试2", Color.Blue))
                    lineCharts.add(LineChartData(10f, "测试3", Color.Blue))
                    lineCharts.add(LineChartData(75f, "测试4", Color.Blue))
                    lineCharts.add(LineChartData(25f, "测试5", Color.Blue))
                    LineChart(lineDataValues = lineCharts, maxValue = 100, minValue = 0)

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeChartTheme {
        Greeting("Android")
    }
}