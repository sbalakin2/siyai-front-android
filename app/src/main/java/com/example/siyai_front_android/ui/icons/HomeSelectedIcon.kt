package com.example.siyai_front_android.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val HomeSelectedIcon: ImageVector
    get() {
        if (_HomeSelectedIcon != null) {
            return _HomeSelectedIcon!!
        }
        _HomeSelectedIcon = ImageVector.Builder(
            name = "HomeSelectedIcon",
            defaultWidth = 512.dp,
            defaultHeight = 512.dp,
            viewportWidth = 512f,
            viewportHeight = 512f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(234.9f, 45f)
                curveToRelative(-10.8f, 2.2f, -23.9f, 7.5f, -33.2f, 13.4f)
                curveToRelative(-6.8f, 4.3f, -122.3f, 99f, -131.6f, 107.9f)
                curveToRelative(-10.9f, 10.5f, -19.8f, 25.7f, -24.3f, 41.2f)
                lineToRelative(-2.3f, 8f)
                lineToRelative(0f, 90f)
                curveToRelative(0f, 85.2f, 0.1f, 90.4f, 1.9f, 97f)
                curveToRelative(8.9f, 32.8f, 33.1f, 57.3f, 63.9f, 64.5f)
                curveToRelative(8.1f, 1.9f, 11.3f, 2.1f, 40.5f, 1.8f)
                lineToRelative(31.7f, -0.3f)
                lineToRelative(6.7f, -3.2f)
                curveToRelative(9.4f, -4.4f, 16f, -10.9f, 20.6f, -20.2f)
                lineToRelative(3.7f, -7.5f)
                lineToRelative(0.5f, -45f)
                curveToRelative(0.6f, -43f, 0.7f, -45.4f, 2.7f, -50.9f)
                curveToRelative(1.2f, -3.2f, 3.3f, -7.7f, 4.7f, -10f)
                curveToRelative(3.5f, -6.1f, 12.7f, -14.4f, 19.3f, -17.5f)
                curveToRelative(5.2f, -2.4f, 7f, -2.7f, 16.3f, -2.7f)
                curveToRelative(9.3f, -0f, 11.1f, 0.3f, 16.3f, 2.7f)
                curveToRelative(6.6f, 3.1f, 15.8f, 11.4f, 19.4f, 17.6f)
                curveToRelative(1.4f, 2.3f, 3.5f, 7.1f, 4.6f, 10.5f)
                curveToRelative(2f, 5.8f, 2.1f, 9.1f, 2.6f, 50.8f)
                lineToRelative(0.6f, 44.5f)
                lineToRelative(3.7f, 7.5f)
                curveToRelative(4.6f, 9.3f, 11.2f, 15.8f, 20.6f, 20.2f)
                lineToRelative(6.7f, 3.2f)
                lineToRelative(31.7f, 0.3f)
                curveToRelative(29.2f, 0.3f, 32.4f, 0.1f, 40.5f, -1.8f)
                curveToRelative(26.8f, -6.3f, 49.1f, -25.9f, 59.6f, -52.2f)
                curveToRelative(6.5f, -16.2f, 6.1f, -9.8f, 6.5f, -105.2f)
                curveToRelative(0.3f, -95.9f, 0.3f, -95.6f, -6f, -111.9f)
                curveToRelative(-3.8f, -9.8f, -12.8f, -23.4f, -20.5f, -30.9f)
                curveToRelative(-10.7f, -10.4f, -124.7f, -103.9f, -132.8f, -108.9f)
                curveToRelative(-21.4f, -13.3f, -49.8f, -18.2f, -74.6f, -12.9f)
                close()
            }
        }.build()

        return _HomeSelectedIcon!!
    }

@Suppress("ObjectPropertyName")
private var _HomeSelectedIcon: ImageVector? = null
