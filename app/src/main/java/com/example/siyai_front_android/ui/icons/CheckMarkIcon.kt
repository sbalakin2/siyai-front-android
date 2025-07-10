package com.example.siyai_front_android.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val CheckMarkIcon: ImageVector
    get() {
        if (_CheckMarkIcon != null) {
            return _CheckMarkIcon!!
        }
        _CheckMarkIcon = ImageVector.Builder(
            name = "CheckMarkIcon",
            defaultWidth = 140.dp,
            defaultHeight = 140.dp,
            viewportWidth = 140f,
            viewportHeight = 140f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2f
            ) {
                moveTo(70f, 70f)
                moveToRelative(-69.5f, 0f)
                arcToRelative(69.5f, 69.5f, 0f, isMoreThanHalf = true, isPositiveArc = true, 139f, 0f)
                arcToRelative(69.5f, 69.5f, 0f, isMoreThanHalf = true, isPositiveArc = true, -139f, 0f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 4f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(100.61f, 48.81f)
                lineTo(62.09f, 93.85f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 4f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(61.38f, 93.23f)
                lineTo(41.18f, 75.84f)
            }
        }.build()

        return _CheckMarkIcon!!
    }

@Suppress("ObjectPropertyName")
private var _CheckMarkIcon: ImageVector? = null
