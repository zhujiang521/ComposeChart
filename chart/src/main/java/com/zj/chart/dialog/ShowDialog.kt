package com.zj.chart.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun ShowDialog(
    alertDialog: MutableState<Boolean>,
    title: String,
    content: String,
    cancelString: String,
    confirmString: String,
    onConfirmListener: () -> Unit
) {
    if (!alertDialog.value) return
    val buttonHeight = 45.dp
    Dialog(onDismissRequest = {
        alertDialog.value = false
    }) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                Text(
                    text = content,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(
                        top = 12.dp, bottom = 25.dp,
                        start = 20.dp, end = 20.dp
                    )
                )
                Divider()
                Row {
                    TextButton(
                        modifier = Modifier
                            .weight(1f)
                            .height(buttonHeight),
                        onClick = {
                            alertDialog.value = false
                        }
                    ) {
                        Text(
                            text = cancelString,
                            fontSize = 16.sp,
                            color = Color(red = 53, green = 128, blue = 186)
                        )
                    }
                    Divider(
                        modifier = Modifier
                            .width(1.dp)
                            .height(buttonHeight)
                    )
                    TextButton(
                        modifier = Modifier
                            .weight(1f)
                            .height(buttonHeight),
                        onClick = {
                            alertDialog.value = false
                            onConfirmListener()
                        }
                    ) {
                        Text(
                            text = confirmString,
                            fontSize = 16.sp,
                            color = Color(red = 53, green = 128, blue = 186)
                        )
                    }
                }
            }
        }
    }
}
