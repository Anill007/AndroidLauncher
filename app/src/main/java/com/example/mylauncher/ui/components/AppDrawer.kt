package com.example.mylauncher.ui.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import com.example.mylauncher.model.LauncherApp
import com.example.mylauncher.model.LauncherTheme

@Composable
fun AppDrawer(
    modifier: Modifier,
    theme: LauncherTheme,
    onAppClick: (String) -> Unit,
    onDrag: (Float) -> Unit,
    onDragEnd: (Float) -> Unit
) {
    val context = LocalContext.current

    val apps = remember {
        val intent = Intent(Intent.ACTION_MAIN)
            .addCategory(Intent.CATEGORY_LAUNCHER)

        context.packageManager.queryIntentActivities(intent, 0)
            .map {
                LauncherApp(
                    name = it.loadLabel(context.packageManager).toString(),
                    packageName = it.activityInfo.packageName,
                    icon = it.loadIcon(context.packageManager)
                )
            }
            .distinctBy { it.packageName }
            .sortedBy { it.name.lowercase() }
    }

    Column(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = 28.dp,
                    topEnd = 28.dp
                )
            )
            .background(theme.surface)
            .border(
                1.dp,
                theme.accent.copy(alpha = 0.65f),
                RoundedCornerShape(
                    topStart = 28.dp,
                    topEnd = 28.dp
                )
            )
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(38.dp)
                .draggable(
                    orientation = Orientation.Vertical,
                    state = rememberDraggableState { delta ->
                        onDrag(delta)
                    },
                    onDragStopped = { velocity ->
                        onDragEnd(velocity)
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(38.dp)
                    .height(5.dp)
                    .clip(CircleShape)
                    .background(theme.accent)
            )
        }

        Text(
            text = "Apps",
            color = theme.text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            items(apps, key = { it.packageName }) { app ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onAppClick(app.packageName)
                        }
                        .padding(vertical = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        bitmap = app.icon
                            .toBitmap(96, 96)
                            .asImageBitmap(),
                        contentDescription = app.name,
                        modifier = Modifier.size(48.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(Modifier.height(6.dp))

                    Text(
                        text = app.name,
                        color = theme.text,
                        fontSize = 11.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
