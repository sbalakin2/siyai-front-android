package com.example.siyai_front_android.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val CloseIcon: ImageVector
    get() {
        if (_CloseIcon != null) {
            return _CloseIcon!!
        }
        _CloseIcon = ImageVector.Builder(
            name = "CloseIcon",
            defaultWidth = 512.dp,
            defaultHeight = 512.dp,
            viewportWidth = 512f,
            viewportHeight = 512f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(27.1f, 1.1f)
                curveToRelative(-10.4f, 2f, -19.4f, 9.1f, -23.9f, 18.7f)
                curveToRelative(-2.2f, 4.7f, -2.7f, 7.1f, -2.7f, 13.7f)
                curveToRelative(0f, 15.5f, -6.8f, 7.8f, 107.5f, 122f)
                lineToRelative(101f, 101f)
                lineToRelative(-101.9f, 102f)
                curveToRelative(-82.7f, 82.9f, -102.2f, 102.9f, -104.1f, 107f)
                curveToRelative(-3.4f, 7.1f, -3.5f, 19.9f, -0.2f, 27f)
                curveToRelative(2.9f, 6.3f, 9.3f, 12.9f, 15.7f, 16.3f)
                curveToRelative(7.2f, 3.7f, 18.7f, 4.2f, 27f, 1.2f)
                curveToRelative(5.8f, -2.2f, 9.4f, -5.6f, 108.3f, -104.4f)
                lineToRelative(102.2f, -102.1f)
                lineToRelative(101.8f, 101.6f)
                curveToRelative(81.7f, 81.6f, 102.7f, 102.1f, 106.7f, 104f)
                curveToRelative(4.2f, 2f, 6.5f, 2.4f, 14.5f, 2.4f)
                curveToRelative(8.3f, -0f, 10.1f, -0.4f, 14.5f, -2.7f)
                curveToRelative(6.2f, -3.2f, 12.9f, -10.3f, 15.8f, -16.6f)
                curveToRelative(3.2f, -6.8f, 3f, -19.7f, -0.3f, -26.7f)
                curveToRelative(-1.9f, -4.1f, -21.4f, -24.1f, -104.1f, -107f)
                lineToRelative(-101.9f, -102f)
                lineToRelative(101f, -101f)
                curveToRelative(114.3f, -114.2f, 107.5f, -106.5f, 107.5f, -122f)
                curveToRelative(0f, -6.5f, -0.5f, -9f, -2.6f, -13.5f)
                curveToRelative(-8.1f, -17.2f, -28.4f, -24.2f, -45.4f, -15.8f)
                curveToRelative(-2.6f, 1.3f, -39.1f, 37f, -105.7f, 103.6f)
                lineToRelative(-101.8f, 101.7f)
                lineToRelative(-101.7f, -101.7f)
                curveToRelative(-66.7f, -66.6f, -103.2f, -102.3f, -105.8f, -103.6f)
                curveToRelative(-3.4f, -1.7f, -14.5f, -4.5f, -16.4f, -4.1f)
                curveToRelative(-0.3f, 0.1f, -2.6f, 0.5f, -5f, 1f)
                close()
            }
        }.build()

        return _CloseIcon!!
    }

private var _CloseIcon: ImageVector? = null
