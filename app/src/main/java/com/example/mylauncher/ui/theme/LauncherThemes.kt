package com.example.mylauncher.ui.theme

import androidx.compose.ui.graphics.Color
import com.example.mylauncher.model.LauncherTheme

val launcherThemes = listOf(
    // 1. Porcelain
    LauncherTheme(
        "Porcelain",
        Color(0xFFFFFFFF),
        Color(0xFFF7F8FA),
        Color(0xFF171923),
        Color(0xFF737987),
        Color(0xFFFFFFFF),
        Color(0xFFDDE1E7),
        Color(0xFFBFC5D2)
    ),

    // 2. Obsidian
    LauncherTheme(
        "Obsidian",
        Color(0xFF050607),
        Color(0xFF111318),
        Color.White,
        Color(0xFFAAAAAA),
        Color(0xFF050607),
        Color(0xFF555D70),
        Color(0xFF858B99)
    ),

    // 3. Amethyst
    LauncherTheme(
        "Amethyst",
        Color(0xFF16072D),
        Color(0xFF24113D),
        Color.White,
        Color(0xFFD0BCEB),
        Color(0xFFAC65FF),
        Color(0xFF853BDB),
        Color(0xFFE0B7FF)
    ),

    // 4. Ember
    LauncherTheme(
        "Ember",
        Color(0xFF260D04),
        Color(0xFF32160C),
        Color.White,
        Color(0xFFE8B9A0),
        Color(0xFFFF8A24),
        Color(0xFFFF641C),
        Color(0xFFFFC078)
    ),

    // 5. Neon
    LauncherTheme(
        "Neon",
        Color(0xFF050510),
        Color(0xFF101025),
        Color(0xFFF5F2FF),
        Color(0xFFB8B7D9),
        Color(0xFF00F5FF),
        Color(0xFFFF36C7),
        Color(0xFF9B5CFF)
    )
)
