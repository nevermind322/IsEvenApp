package com.example.drawInput

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Picture
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.core.graphics.record
import androidx.hilt.navigation.compose.hiltViewModel
import kotlin.math.roundToInt

internal const val TAG = "classifier"

@Composable
fun DrawInputScreen(vm: DrawInputViewModel = hiltViewModel()) {

    val state by vm.state.collectAsState()
    Column {


        DrawCanvas { vm.classify(it) }

        when (state) {
            DrawInputScreenUiState.Loading -> CircularProgressIndicator()
            is DrawInputScreenUiState.Error -> Text(text = (state as DrawInputScreenUiState.Error).msg)
            DrawInputScreenUiState.Greeting -> Text("Hello, please draw a number")
            is DrawInputScreenUiState.Success -> {
                val res = (state as DrawInputScreenUiState.Success).data
                Text(text = "Number is ${res.number} and it is ${if (res.isEven.isEven) "even" else "odd"}")
            }
        }
    }
}


private enum class MotionEvent() {
    Idle, Up, Down, Move
}

@Composable
fun DrawCanvas(classify: (Bitmap) -> Unit) {

    var offset by remember { mutableStateOf(Offset.Unspecified) }
    var path by remember { mutableStateOf(Path()) }
    var motionEvent by remember { mutableStateOf(MotionEvent.Idle) }

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
            paint.strokeWidth = 10f
            picture.record(width, height) { drawPath(path.asAndroidPath(), paint) }
            val bitmap = Bitmap.createBitmap(picture.width, picture.height, Bitmap.Config.ARGB_8888)

            val canvas = android.graphics.Canvas(bitmap)
            canvas.drawColor(android.graphics.Color.WHITE)
            canvas.drawPicture(picture)

            classify(bitmap)
        }) { Text(text = "save") }

        Canvas(modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .pointerInput(Unit) {
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
            }
            .border(2.dp, Color.Red)) {
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
        }
    }
}