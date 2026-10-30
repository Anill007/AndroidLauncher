package com.example.mylauncher.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mylauncher.ui.theme.launcherThemes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun ClockLauncher() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val drawerProgress = remember { Animatable(0f) }

    var selectedTheme by remember { mutableIntStateOf(0) }
    var time by remember { mutableStateOf(LocalDateTime.now()) }

    val theme = launcherThemes[selectedTheme]

    LaunchedEffect(Unit) {
        while (true) {
            time = LocalDateTime.now()
            delay(1000L.milliseconds)
        }
    }

    BackHandler {
        scope.launch {
            drawerProgress.animateTo(0f, spring())
        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(theme.background)
            .pointerInput(Unit) {
                var drag = 0f

                detectVerticalDragGestures(
                    onVerticalDrag = { _, amount ->
                        drag += amount
                    },
                    onDragEnd = {
                        if (drag < -40f) {
                            scope.launch {
                                drawerProgress.animateTo(1f, spring())
                            }
                        }
                        drag = 0f
                    },
                    onDragCancel = {
                        drag = 0f
                    }
                )
            }
    ) {
        val drawerHeight = maxHeight * 0.8f
        val drawerHeightPx = with(LocalDensity.current) {
            drawerHeight.toPx()
        }

        ThemeBackground(
            theme = theme,
            modifier = Modifier.fillMaxSize()
        )

        // Stylish centered greeting
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-145).dp)
                .graphicsLayer {
                    alpha = 1f - drawerProgress.value
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "A LITTLE MOMENT FOR YOU",
                color = theme.secondary,
                fontSize = 10.sp,
                letterSpacing = 3.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Good to see you,",
                color = theme.text,
                fontSize = 25.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic
            )

            Text(
                text = "Anil.",
                color = theme.text,
                fontSize = 34.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(5.dp))

            Text(
                text = "Make today yours ✦",
                color = theme.secondary,
                fontSize = 12.sp,
                letterSpacing = 1.sp
            )
        }

        // Clock and date
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer {
                    translationY =
                        -constraints.maxHeight * 0.35f * drawerProgress.value
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = time.format(
                    DateTimeFormatter.ofPattern("HH:mm")
                ),
                color = theme.text,
                fontSize = (68f - 32f * drawerProgress.value).sp,
                fontWeight = FontWeight.Light,
                letterSpacing = (-2).sp
            )

            Text(
                text = time.format(
                    DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")
                ),
                color = theme.secondary,
                fontSize = (16f - 2f * drawerProgress.value).sp
            )
        }

        // Theme selection buttons
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 55.dp)
                .graphicsLayer {
                    alpha = 1f - drawerProgress.value
                },
            horizontalArrangement = Arrangement.spacedBy(17.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            launcherThemes.forEachIndexed { index, item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .shadow(8.dp, CircleShape)
                            .clip(CircleShape)
                            .background(item.accent)
                            .border(
                                width = if (selectedTheme == index) 2.dp else 1.dp,
                                color = when (index) {
                                    0 -> Color(0xFFB8BDC7)
                                    1 -> Color(0xFF777777)
                                    else -> item.text.copy(alpha = 0.7f)
                                },
                                shape = CircleShape
                            )
                            .clickable {
                                if (drawerProgress.value < 0.01f) {
                                    selectedTheme = index
                                }
                            }
                    )

                    Spacer(Modifier.height(7.dp))

                    Text(
                        text = item.name,
                        color = theme.secondary,
                        fontSize = 10.sp
                    )
                }
            }
        }

        // App drawer
        AppDrawer(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset {
                    IntOffset(
                        0,
                        (drawerHeightPx * (1f - drawerProgress.value))
                            .roundToInt()
                    )
                }
                .fillMaxWidth()
                .height(drawerHeight),
            theme = theme,
            onAppClick = { packageName ->
                context.packageManager
                    .getLaunchIntentForPackage(packageName)
                    ?.let(context::startActivity)
            },
            onDrag = { delta ->
                scope.launch {
                    drawerProgress.snapTo(
                        (drawerProgress.value - delta / drawerHeightPx)
                            .coerceIn(0f, 1f)
                    )
                }
            },
            onDragEnd = { velocity ->
                scope.launch {
                    val target = when {
                        velocity < -500f -> 1f
                        velocity > 500f -> 0f
                        drawerProgress.value > 0.5f -> 1f
                        else -> 0f
                    }

                    drawerProgress.animateTo(target, spring())
                }
            }
        )
    }
}
