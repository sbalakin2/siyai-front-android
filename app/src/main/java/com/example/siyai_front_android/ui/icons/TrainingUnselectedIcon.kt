package com.example.siyai_front_android.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val TrainingUnselectedIcon: ImageVector
    get() {
        if (_TrainingUnselected != null) {
            return _TrainingUnselected!!
        }
        _TrainingUnselected = ImageVector.Builder(
            name = "TrainingUnselected",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFC1BDB3)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(5f, 5f)
                lineTo(16f, 5f)
                arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 18f, 7f)
                lineTo(18f, 17f)
                arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 16f, 19f)
                lineTo(5f, 19f)
                arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 3f, 17f)
                lineTo(3f, 7f)
                arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 5f, 5f)
                close()
            }
            path(
                stroke = SolidColor(Color(0xFFC1BDB3)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(20f, 7f)
                lineToRelative(3f, -2f)
                lineToRelative(0f, 14f)
                lineToRelative(-3f, -2f)
                lineToRelative(0f, -10f)
                close()
            }
        }.build()

        return _TrainingUnselected!!
    }

@Suppress("ObjectPropertyName")
private var _TrainingUnselected: ImageVector? = null
