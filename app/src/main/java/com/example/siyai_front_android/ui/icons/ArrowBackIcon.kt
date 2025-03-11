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

val ArrowBackIcon: ImageVector
    get() {
        if (_arrowbackicon != null) {
            return _arrowbackicon!!
        }
        _arrowbackicon = Builder(name = "ArrowBackIcon", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF1f1f1f)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveToRelative(382.0f, 480.0f)
                lineToRelative(294.0f, 294.0f)
                quadToRelative(15.0f, 15.0f, 14.5f, 35.0f)
                reflectiveQuadTo(675.0f, 844.0f)
                quadToRelative(-15.0f, 15.0f, -35.0f, 15.0f)
                reflectiveQuadToRelative(-35.0f, -15.0f)
                lineTo(297.0f, 537.0f)
                quadToRelative(-12.0f, -12.0f, -18.0f, -27.0f)
                reflectiveQuadToRelative(-6.0f, -30.0f)
                quadToRelative(0.0f, -15.0f, 6.0f, -30.0f)
                reflectiveQuadToRelative(18.0f, -27.0f)
                lineToRelative(308.0f, -308.0f)
                quadToRelative(15.0f, -15.0f, 35.5f, -14.5f)
                reflectiveQuadTo(676.0f, 116.0f)
                quadToRelative(15.0f, 15.0f, 15.0f, 35.0f)
                reflectiveQuadToRelative(-15.0f, 35.0f)
                lineTo(382.0f, 480.0f)
                close()
            }
        }
        .build()
        return _arrowbackicon!!
    }

private var _arrowbackicon: ImageVector? = null
