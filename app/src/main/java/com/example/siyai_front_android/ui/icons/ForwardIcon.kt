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

val ForwardIcon: ImageVector
    get() {
        if (_forwardicon != null) {
            return _forwardicon!!
        }
        _forwardicon = Builder(name = "ForwardIcon", defaultWidth = 20.0.dp, defaultHeight =
                20.0.dp, viewportWidth = 512.0f, viewportHeight = 512.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0x00000000)),
                    strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(135.5f, 1.3f)
                curveToRelative(-6.3f, 2.1f, -9.7f, 4.2f, -14.2f, 8.9f)
                curveToRelative(-6.2f, 6.4f, -8.8f, 12.7f, -8.7f, 21.8f)
                curveToRelative(0.0f, 14.7f, -7.4f, 6.4f, 107.3f, 121.3f)
                lineToRelative(102.6f, 102.7f)
                lineToRelative(-102.6f, 102.8f)
                curveToRelative(-114.7f, 114.8f, -107.3f, 106.5f, -107.3f, 121.2f)
                curveToRelative(-0.1f, 9.3f, 2.5f, 15.5f, 9.1f, 22.2f)
                curveToRelative(6.4f, 6.5f, 12.8f, 9.2f, 22.3f, 9.2f)
                curveToRelative(15.0f, 0.2f, 6.0f, 8.2f, 135.6f, -121.6f)
                curveToRelative(93.8f, -94.0f, 115.5f, -116.2f, 117.4f, -120.3f)
                curveToRelative(3.4f, -7.2f, 3.4f, -19.9f, 0.1f, -27.0f)
                curveToRelative(-1.9f, -4.0f, -24.6f, -27.3f, -117.5f, -120.3f)
                curveToRelative(-102.5f, -102.7f, -115.8f, -115.6f, -121.3f, -118.4f)
                curveToRelative(-7.2f, -3.5f, -16.5f, -4.6f, -22.8f, -2.5f)
                close()
            }
        }
        .build()
        return _forwardicon!!
    }

private var _forwardicon: ImageVector? = null