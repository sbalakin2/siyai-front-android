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

val StopIcon: ImageVector
    get() {
        if (_stop != null) {
            return _stop!!
        }
        _stop = Builder(name = "Stop", defaultWidth = 512.0.dp, defaultHeight = 512.0.dp,
                viewportWidth = 512.0f, viewportHeight = 512.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0x00000000)),
                    strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(96.0f, 65.4f)
                curveToRelative(-14.7f, 4.1f, -26.7f, 16.2f, -30.6f, 31.0f)
                curveToRelative(-2.0f, 7.6f, -2.0f, 311.6f, 0.0f, 319.2f)
                curveToRelative(4.0f, 15.0f, 16.0f, 27.0f, 31.1f, 31.0f)
                curveToRelative(6.6f, 1.8f, 309.3f, 2.1f, 317.7f, 0.3f)
                curveToRelative(15.7f, -3.3f, 29.3f, -17.0f, 32.7f, -32.7f)
                curveToRelative(1.6f, -7.5f, 1.6f, -308.9f, 0.0f, -316.4f)
                curveToRelative(-3.3f, -15.3f, -15.8f, -28.3f, -31.3f, -32.4f)
                curveToRelative(-7.2f, -1.9f, -312.7f, -1.9f, -319.6f, -0.0f)
                close()
            }
        }
        .build()
        return _stop!!
    }

private var _stop: ImageVector? = null