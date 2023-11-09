package com.example.drawInput

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Picture
import android.os.Build
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.geometry.isUnspecified
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.core.graphics.record
import androidx.hilt.navigation.compose.hiltViewModel
import kotlin.math.roundToInt

@Composable
fun DrawInputScreen() {
    Column {
        LogPointerEvents(PointerEventType.Move)
        DrawCanvas()
    }
}

@Composable
private fun LogPointerEvents(filter: PointerEventType? = null) {
    var log by remember { mutableStateOf("") }
    Column {
        Text(log)
        Box(
            Modifier
                .size(100.dp)
                .background(Color.Red)
                .pointerInput(filter) {
                    awaitPointerEventScope {
                        while (true) {
                            val event = awaitPointerEvent()
                            // handle pointer event
                            if (filter == null || event.type == filter) {
                                log = "${event.type}, ${event.changes.first().position}"
                            }
                        }
                    }
                })
    }
}


private enum class MotionEvent() {
    Idle, Up, Down, Move
}

@Composable
fun DrawCanvas(vm: DrawInputViewModel = hiltViewModel()) {
    val filter = PointerEventType.Move
    var offset by remember { mutableStateOf(Offset.Unspecified) }
    var path by remember { mutableStateOf(Path()) }
    var motionEvent by remember { mutableStateOf(MotionEvent.Idle) }
    val state by vm.state.collectAsState()

    var width = remember { 0 }
    var height = remember { 0 }

    Column {

        Button(onClick = {
            path = Path()
            offset = Offset.Unspecified
        }) { Text("clear") }

        Button(onClick = {
            val picture = Picture()
            val paint = Paint()
            paint.style = Paint.Style.STROKE
            paint.color = Color.Green.toArgb()
            paint.strokeWidth = 8f
            picture.record(width, height) { drawPath(path.asAndroidPath(), paint) }
            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                Bitmap.createBitmap(picture)
            } else Bitmap.createBitmap(
                picture.width, picture.height, Bitmap.Config.ARGB_8888
            ).also {
                val canvas = android.graphics.Canvas(it)
                canvas.drawColor(android.graphics.Color.WHITE)
                canvas.drawPicture(picture)
            }
            vm.classify(bitmap)

        }) {
            Text(text = "save")
        }
        if (state != -1) Text(text = state.toString())
        else Canvas(modifier = Modifier
            .fillMaxSize()
            .pointerInput(filter) {
                awaitEachGesture {
                    awaitFirstDown().also {
                        motionEvent = MotionEvent.Down
                        offset = it.position
                    }
                    do {
                        val event = awaitPointerEvent()
                        event.changes.forEach { it.consume() }
                        val change = event.changes.first()
                        if (offset.isUnspecified) path.moveTo(
                            change.position.x, change.position.y
                        )
                        offset = change.position
                        motionEvent = MotionEvent.Move
                    } while (event.changes.any { it.pressed })
                    motionEvent = MotionEvent.Up
                }
            }, onDraw = {

            width = this.size.width.roundToInt()
            height = this.size.height.roundToInt()

            when (motionEvent) {
                MotionEvent.Idle -> Unit
                MotionEvent.Move -> {
                    if (offset.isSpecified) path.lineTo(offset.x, offset.y)
                }

                MotionEvent.Down -> path.moveTo(offset.x, offset.y)
                MotionEvent.Up -> {
                    path.lineTo(offset.x, offset.y)
                    motionEvent = MotionEvent.Idle
                }
            }

            drawPath(
                path, Color.Red, style = Stroke(
                    width = 8.dp.toPx(), join = StrokeJoin.Round, cap = StrokeCap.Round
                )
            )
        })
    }
}