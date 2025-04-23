package com.example.siyai_front_android.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val EditIcon: ImageVector
    get() {
        if (_edit != null) {
            return _edit!!
        }
        _edit = Builder(name = "EditIcon", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 512.0f, viewportHeight = 512.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0x00000000)),
                    strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(372.0f, 49.1f)
                curveToRelative(-14.5f, 2.2f, -26.8f, 7.8f, -39.3f, 17.9f)
                curveToRelative(-4.0f, 3.2f, -63.7f, 62.4f, -132.9f, 131.6f)
                lineToRelative(-125.6f, 125.9f)
                lineToRelative(-1.5f, 7.0f)
                curveToRelative(-21.0f, 97.5f, -24.9f, 116.7f, -24.3f, 120.0f)
                curveToRelative(0.9f, 4.6f, 5.7f, 10.1f, 10.3f, 11.6f)
                curveToRelative(1.9f, 0.6f, 5.4f, 0.9f, 7.7f, 0.6f)
                curveToRelative(3.0f, -0.4f, 98.3f, -20.7f, 118.6f, -25.2f)
                curveToRelative(2.7f, -0.7f, 252.6f, -249.6f, 260.9f, -260.0f)
                curveToRelative(27.9f, -34.9f, 22.4f, -86.0f, -12.1f, -113.1f)
                curveToRelative(-16.9f, -13.2f, -40.8f, -19.5f, -61.8f, -16.3f)
                close()
                moveTo(394.3f, 81.5f)
                curveToRelative(24.3f, 5.1f, 41.0f, 29.4f, 36.9f, 53.7f)
                curveToRelative(-2.3f, 13.9f, -5.9f, 19.3f, -27.5f, 41.3f)
                lineToRelative(-19.2f, 19.6f)
                lineToRelative(-34.0f, -34.1f)
                lineToRelative(-34.0f, -34.0f)
                lineToRelative(20.1f, -20.0f)
                curveToRelative(17.4f, -17.4f, 20.9f, -20.4f, 26.6f, -22.9f)
                curveToRelative(11.0f, -5.0f, 19.7f, -6.0f, 31.1f, -3.6f)
                close()
                moveTo(325.5f, 255.1f)
                curveToRelative(-19.8f, 19.8f, -62.8f, 62.5f, -95.5f, 94.9f)
                lineToRelative(-59.6f, 58.8f)
                lineToRelative(-42.3f, 9.1f)
                curveToRelative(-23.3f, 5.0f, -42.5f, 9.0f, -42.7f, 8.8f)
                curveToRelative(-0.1f, -0.1f, 3.8f, -19.4f, 8.8f, -42.7f)
                lineToRelative(9.0f, -42.5f)
                lineToRelative(95.1f, -95.3f)
                lineToRelative(95.2f, -95.2f)
                lineToRelative(34.0f, 34.0f)
                lineToRelative(34.0f, 34.0f)
                lineToRelative(-36.0f, 36.1f)
                close()
            }
        }
        .build()
        return _edit!!
    }

private var _edit: ImageVector? = null