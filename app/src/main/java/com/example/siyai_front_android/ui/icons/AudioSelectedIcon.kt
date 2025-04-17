package com.example.siyai_front_android.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val AudioSelectedIcon: ImageVector
    get() {
        if (_AudioSelectedIcon != null) {
            return _AudioSelectedIcon!!
        }
        _AudioSelectedIcon = ImageVector.Builder(
            name = "AudioSelectedIcon",
            defaultWidth = 512.dp,
            defaultHeight = 512.dp,
            viewportWidth = 512f,
            viewportHeight = 512f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(319.5f, 23.5f)
                curveToRelative(-156.7f, 22.6f, -163.1f, 23.6f, -167.5f, 26.3f)
                curveToRelative(-2.6f, 1.6f, -5.7f, 4.8f, -7.5f, 7.6f)
                lineToRelative(-3f, 4.9f)
                lineToRelative(-0.3f, 138.4f)
                curveToRelative(-0.1f, 76.1f, -0.4f, 138.3f, -0.6f, 138.3f)
                curveToRelative(-0.2f, -0f, -3.7f, -1.6f, -7.7f, -3.5f)
                curveToRelative(-12.9f, -6.1f, -21.3f, -7.8f, -38.4f, -7.8f)
                curveToRelative(-12.2f, -0f, -16.4f, 0.4f, -22.7f, 2.1f)
                curveToRelative(-36.1f, 9.9f, -61.9f, 38.1f, -68.3f, 74.8f)
                curveToRelative(-1.9f, 10.9f, -1.9f, 18.8f, 0f, 29.8f)
                curveToRelative(3.6f, 20.5f, 12.9f, 38.2f, 27.4f, 51.8f)
                curveToRelative(36f, 34f, 91.3f, 34.2f, 126.8f, 0.5f)
                curveToRelative(12.8f, -12.2f, 21.7f, -27f, 26.5f, -44.2f)
                lineToRelative(2.3f, -8f)
                lineToRelative(0.3f, -126.6f)
                lineToRelative(0.2f, -126.6f)
                lineToRelative(135.3f, -19.2f)
                curveToRelative(74.3f, -10.7f, 136.7f, -19.6f, 138.5f, -19.9f)
                lineToRelative(3.2f, -0.5f)
                lineToRelative(0f, 75.7f)
                curveToRelative(0f, 41.6f, -0.2f, 75.6f, -0.4f, 75.6f)
                curveToRelative(-0.2f, -0f, -3.7f, -1.6f, -7.7f, -3.5f)
                curveToRelative(-13f, -6.1f, -21.4f, -7.8f, -38.4f, -7.9f)
                curveToRelative(-16.4f, -0.1f, -23.3f, 1.3f, -37.6f, 7.4f)
                curveToRelative(-19.4f, 8.2f, -39.1f, 28.2f, -47.3f, 47.7f)
                curveToRelative(-5.8f, 13.8f, -7.1f, 20.7f, -7f, 36.8f)
                curveToRelative(0.1f, 17.1f, 1.8f, 25.4f, 8f, 38.4f)
                curveToRelative(27.6f, 58.5f, 102.9f, 72.2f, 148.4f, 27.1f)
                curveToRelative(13.2f, -13f, 21.5f, -27.8f, 25.7f, -45.6f)
                curveToRelative(1.7f, -7.5f, 1.8f, -16.4f, 1.8f, -192.5f)
                lineToRelative(0f, -184.6f)
                lineToRelative(-3f, -4.9f)
                curveToRelative(-4.1f, -6.6f, -10.5f, -10.5f, -18.2f, -10.9f)
                curveToRelative(-3.9f, -0.3f, -57.6f, 7f, -168.8f, 23f)
                close()
            }
        }.build()

        return _AudioSelectedIcon!!
    }

@Suppress("ObjectPropertyName")
private var _AudioSelectedIcon: ImageVector? = null
