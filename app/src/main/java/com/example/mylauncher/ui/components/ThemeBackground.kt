package com.example.mylauncher.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.mylauncher.model.LauncherTheme

@Composable
fun ThemeBackground(
    theme: LauncherTheme,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        drawRect(theme.background)

        when (theme.name) {
            "Porcelain" -> {
                drawCircle(
                    brush = Brush.radialGradient(
                        listOf(
                            Color(0xFFE9EDF3).copy(alpha = 0.8f),
                            Color.Transparent
                        ),
                        center = Offset(w * 0.85f, h * 0.35f),
                        radius = w * 0.65f
                    ),
                    radius = w * 0.65f,
                    center = Offset(w * 0.85f, h * 0.35f)
                )

                val path = Path().apply {
                    moveTo(-w * 0.1f, h * 0.48f)
                    cubicTo(
                        w * 0.3f, h * 0.32f,
                        w * 0.65f, h * 0.65f,
                        w * 1.1f, h * 0.42f
                    )
                }

                drawPath(
                    path,
                    Color(0xFFBBC3D0).copy(alpha = 0.5f),
                    style = Stroke(width = 2.dp.toPx())
                )

                drawCircle(
                    Color(0xFFE2E6EC).copy(alpha = 0.65f),
                    radius = w * 0.22f,
                    center = Offset(w * 0.1f, h * 0.75f)
                )
            }

            "Obsidian" -> {
                drawCircle(
                    brush = Brush.radialGradient(
                        listOf(
                            Color(0xFF303745).copy(alpha = 0.35f),
                            Color.Transparent
                        ),
                        center = Offset(w * 0.85f, h * 0.35f),
                        radius = w * 0.65f
                    ),
                    radius = w * 0.65f,
                    center = Offset(w * 0.85f, h * 0.35f)
                )

                for (i in 0..3) {
                    drawArc(
                        color = Color(0xFF8992A3).copy(
                            alpha = 0.12f - i * 0.02f
                        ),
                        startAngle = 190f,
                        sweepAngle = 160f,
                        useCenter = false,
                        topLeft = Offset(
                            w * 0.35f - i * 18.dp.toPx(),
                            -h * 0.28f - i * 18.dp.toPx()
                        ),
                        size = Size(
                            w * 0.9f + i * 36.dp.toPx(),
                            h * 0.55f + i * 36.dp.toPx()
                        ),
                        style = Stroke(width = 2.dp.toPx())
                    )
                }

                val path = Path().apply {
                    moveTo(0f, h * 0.72f)
                    cubicTo(
                        w * 0.3f, h * 0.55f,
                        w * 0.7f, h * 0.90f,
                        w, h * 0.68f
                    )
                }

                drawPath(
                    path,
                    Color(0xFF667080).copy(alpha = 0.18f),
                    style = Stroke(width = 3.dp.toPx())
                )
            }

            "Amethyst" -> {
                drawCircle(
                    brush = Brush.radialGradient(
                        listOf(
                            Color(0xFFB76AFF).copy(alpha = 0.32f),
                            Color.Transparent
                        ),
                        center = Offset(w * 0.8f, h * 0.38f),
                        radius = w * 0.55f
                    ),
                    radius = w * 0.55f,
                    center = Offset(w * 0.8f, h * 0.38f)
                )

                drawCircle(
                    Color(0xFF8C42DA).copy(alpha = 0.13f),
                    radius = w * 0.28f,
                    center = Offset(w * 0.08f, h * 0.72f)
                )

                val path = Path().apply {
                    moveTo(-w * 0.1f, h * 0.52f)
                    cubicTo(
                        w * 0.25f, h * 0.30f,
                        w * 0.55f, h * 0.76f,
                        w * 1.1f, h * 0.48f
                    )
                }

                drawPath(
                    path,
                    Color(0xFFB86BFF).copy(alpha = 0.4f),
                    style = Stroke(width = 3.dp.toPx())
                )

                drawCircle(
                    Color(0xFFE0B7FF).copy(alpha = 0.5f),
                    radius = 4.dp.toPx(),
                    center = Offset(w * 0.78f, h * 0.25f)
                )
            }

            "Ember" -> {
                drawCircle(
                    brush = Brush.radialGradient(
                        listOf(
                            Color(0xFFFF8A24).copy(alpha = 0.3f),
                            Color.Transparent
                        ),
                        center = Offset(w * 0.8f, h * 0.36f),
                        radius = w * 0.58f
                    ),
                    radius = w * 0.58f,
                    center = Offset(w * 0.8f, h * 0.36f)
                )

                drawCircle(
                    Color(0xFFFF9A3C).copy(alpha = 0.10f),
                    radius = w * 0.23f,
                    center = Offset(w * 0.1f, h * 0.75f)
                )

                val path = Path().apply {
                    moveTo(-w * 0.1f, h * 0.45f)
                    cubicTo(
                        w * 0.25f, h * 0.62f,
                        w * 0.60f, h * 0.25f,
                        w * 1.1f, h * 0.55f
                    )
                }

                drawPath(
                    path,
                    Color(0xFFFFA34D).copy(alpha = 0.42f),
                    style = Stroke(width = 3.dp.toPx())
                )

                drawCircle(
                    Color(0xFFFFC078).copy(alpha = 0.35f),
                    radius = 5.dp.toPx(),
                    center = Offset(w * 0.8f, h * 0.27f)
                )
            }

            "Neon" -> {
                drawCircle(
                    brush = Brush.radialGradient(
                        listOf(
                            Color(0xFF00F5FF).copy(alpha = 0.35f),
                            Color.Transparent
                        ),
                        center = Offset(w * 0.8f, h * 0.32f),
                        radius = w * 0.5f
                    ),
                    radius = w * 0.5f,
                    center = Offset(w * 0.8f, h * 0.32f)
                )

                drawCircle(
                    brush = Brush.radialGradient(
                        listOf(
                            Color(0xFFFF36C7).copy(alpha = 0.22f),
                            Color.Transparent
                        ),
                        center = Offset(w * 0.12f, h * 0.75f),
                        radius = w * 0.42f
                    ),
                    radius = w * 0.42f,
                    center = Offset(w * 0.12f, h * 0.75f)
                )

                for (i in 0..7) {
                    val x = w * i / 7f
                    drawLine(
                        Color(0xFF00F5FF).copy(alpha = 0.10f),
                        Offset(x, h * 0.35f),
                        Offset(x, h * 0.9f),
                        strokeWidth = 1.dp.toPx()
                    )
                }

                for (i in 0..9) {
                    val y = h * (0.35f + i * 0.055f)
                    drawLine(
                        Color(0xFF9B5CFF).copy(alpha = 0.10f),
                        Offset(0f, y),
                        Offset(w, y),
                        strokeWidth = 1.dp.toPx()
                    )
                }

                val cyanPath = Path().apply {
                    moveTo(-w * 0.1f, h * 0.48f)
                    cubicTo(
                        w * 0.25f, h * 0.25f,
                        w * 0.6f, h * 0.72f,
                        w * 1.1f, h * 0.40f
                    )
                }

                val pinkPath = Path().apply {
                    moveTo(-w * 0.1f, h * 0.65f)
                    cubicTo(
                        w * 0.35f, h * 0.42f,
                        w * 0.65f, h * 0.88f,
                        w * 1.1f, h * 0.58f
                    )
                }

                drawPath(
                    cyanPath,
                    Color(0xFF00F5FF).copy(alpha = 0.8f),
                    style = Stroke(width = 3.dp.toPx())
                )

                drawPath(
                    pinkPath,
                    Color(0xFFFF36C7).copy(alpha = 0.75f),
                    style = Stroke(width = 3.dp.toPx())
                )

                drawCircle(
                    Color(0xFF00F5FF),
                    radius = 4.dp.toPx(),
                    center = Offset(w * 0.8f, h * 0.28f)
                )
            }
        }
    }
}
