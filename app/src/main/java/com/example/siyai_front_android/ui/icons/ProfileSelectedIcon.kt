package com.example.siyai_front_android.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val ProfileSelectedIcon: ImageVector
    get() {
        if (_ProfileSelectedIcon != null) {
            return _ProfileSelectedIcon!!
        }
        _ProfileSelectedIcon = ImageVector.Builder(
            name = "ProfileSelectedIcon",
            defaultWidth = 512.dp,
            defaultHeight = 512.dp,
            viewportWidth = 512f,
            viewportHeight = 512f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(239.4f, 49.4f)
                curveToRelative(-23f, 4.4f, -40.7f, 13.8f, -55.9f, 29.8f)
                curveToRelative(-12.9f, 13.4f, -21f, 27.6f, -25.6f, 44.8f)
                curveToRelative(-2f, 7.3f, -2.3f, 10.8f, -2.3f, 25.5f)
                curveToRelative(0f, 18.7f, 1.2f, 24.9f, 7.4f, 39.8f)
                curveToRelative(6.6f, 15.8f, 21.5f, 34f, 35.9f, 43.7f)
                curveToRelative(29.9f, 20.1f, 67.5f, 23.1f, 99.7f, 8.1f)
                curveToRelative(11.5f, -5.4f, 19.7f, -11.2f, 29f, -20.5f)
                curveToRelative(13.1f, -13.1f, 21.5f, -27.8f, 26.6f, -46.4f)
                curveToRelative(2.9f, -10.5f, 3.2f, -38.2f, 0.5f, -48.2f)
                curveToRelative(-8.7f, -31.7f, -28.5f, -55.8f, -56.5f, -68.7f)
                curveToRelative(-16.8f, -7.8f, -41.8f, -11.1f, -58.8f, -7.9f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(170.9f, 284.5f)
                curveToRelative(-22.2f, 4.1f, -43.5f, 14.4f, -59.7f, 28.9f)
                curveToRelative(-24f, 21.5f, -37.8f, 48.4f, -41.2f, 80.6f)
                curveToRelative(-1.7f, 16f, 0.1f, 26.6f, 6.7f, 39.2f)
                curveToRelative(6.6f, 12.7f, 20f, 23.7f, 33.8f, 28.1f)
                lineToRelative(7f, 2.2f)
                lineToRelative(138.5f, -0f)
                lineToRelative(138.5f, -0f)
                lineToRelative(7.4f, -2.4f)
                curveToRelative(22.4f, -7.3f, 37.8f, -26.9f, 40.6f, -51.3f)
                curveToRelative(0.8f, -7.6f, -1f, -24.2f, -4.1f, -35.8f)
                curveToRelative(-9.6f, -36.6f, -37.4f, -68.2f, -72f, -82.1f)
                curveToRelative(-4.2f, -1.7f, -12f, -4.3f, -17.3f, -5.7f)
                lineToRelative(-9.6f, -2.7f)
                lineToRelative(-80.5f, -0.2f)
                curveToRelative(-65.1f, -0.1f, -81.9f, 0.1f, -88.1f, 1.2f)
                close()
            }
        }.build()

        return _ProfileSelectedIcon!!
    }

@Suppress("ObjectPropertyName")
private var _ProfileSelectedIcon: ImageVector? = null
