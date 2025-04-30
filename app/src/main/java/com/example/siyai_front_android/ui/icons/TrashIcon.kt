package com.example.siyai_front_android.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val TrashIcon: ImageVector
    get() {
        if (_trashicon != null) {
            return _trashicon!!
        }
        _trashicon = Builder(name = "TrashIcon", defaultWidth = 20.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 33.0f, viewportHeight = 39.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFCCC4BA)),
                    strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(13.75f, 24.375f)
                lineTo(13.75f, 19.5f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFCCC4BA)),
                    strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(19.25f, 24.375f)
                lineTo(19.25f, 19.5f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFCCC4BA)),
                    strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(4.125f, 11.375f)
                horizontalLineTo(28.875f)
                horizontalLineTo(28.75f)
                curveTo(26.8644f, 11.375f, 25.9216f, 11.375f, 25.3358f, 11.9608f)
                curveTo(24.75f, 12.5466f, 24.75f, 13.4894f, 24.75f, 15.375f)
                verticalLineTo(28.5f)
                curveTo(24.75f, 30.3856f, 24.75f, 31.3284f, 24.1642f, 31.9142f)
                curveTo(23.5784f, 32.5f, 22.6356f, 32.5f, 20.75f, 32.5f)
                horizontalLineTo(12.25f)
                curveTo(10.3644f, 32.5f, 9.4216f, 32.5f, 8.8358f, 31.9142f)
                curveTo(8.25f, 31.3284f, 8.25f, 30.3856f, 8.25f, 28.5f)
                verticalLineTo(15.375f)
                curveTo(8.25f, 13.4894f, 8.25f, 12.5466f, 7.6642f, 11.9608f)
                curveTo(7.0784f, 11.375f, 6.1356f, 11.375f, 4.25f, 11.375f)
                horizontalLineTo(4.125f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFCCC4BA)),
                    strokeLineWidth = 2.0f, strokeLineCap = Round, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(13.8437f, 5.4772f)
                curveTo(14.0004f, 5.3045f, 14.3456f, 5.1518f, 14.8259f, 5.0429f)
                curveTo(15.3062f, 4.934f, 15.8946f, 4.875f, 16.5f, 4.875f)
                curveTo(17.1054f, 4.875f, 17.6938f, 4.934f, 18.1741f, 5.0429f)
                curveTo(18.6544f, 5.1518f, 18.9996f, 5.3044f, 19.1563f, 5.4772f)
            }
        }
        .build()
        return _trashicon!!
    }

private var _trashicon: ImageVector? = null
