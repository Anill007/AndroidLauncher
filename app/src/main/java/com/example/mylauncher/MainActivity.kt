package com.example.mylauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mylauncher.ui.components.ClockLauncher
import com.example.mylauncher.ui.theme.MyLauncherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyLauncherTheme {
                ClockLauncher()
            }
        }
    }
}
