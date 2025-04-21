package com.example.siyai_front_android.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val TrainingSelectedIcon: ImageVector
    get() {
        if (_TrainingSelectedIcon != null) {
            return _TrainingSelectedIcon!!
        }
        _TrainingSelectedIcon = ImageVector.Builder(
            name = "TrainingSelectedIcon",
            defaultWidth = 512.dp,
            defaultHeight = 512.dp,
            viewportWidth = 512f,
            viewportHeight = 512f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(85.8f, 107.7f)
                curveToRelative(-18.4f, 3.7f, -36.7f, 21.3f, -41.8f, 40.2f)
                curveToRelative(-1.6f, 6.1f, -1.6f, 210.1f, 0f, 216.2f)
                curveToRelative(4.9f, 18f, 21.9f, 35.1f, 39.8f, 39.9f)
                curveToRelative(3.2f, 0.8f, 35.7f, 1.1f, 119.2f, 1.1f)
                lineToRelative(114.9f, -0f)
                lineToRelative(7f, -2.4f)
                curveToRelative(9.4f, -3.3f, 15.7f, -7.5f, 23f, -15.3f)
                curveToRelative(6.6f, -7f, 12.2f, -17.5f, 14f, -26.2f)
                curveToRelative(1.5f, -7.4f, 1.5f, -203f, 0f, -210.4f)
                curveToRelative(-3.8f, -18.5f, -19.5f, -35.9f, -37.6f, -41.7f)
                lineToRelative(-6.9f, -2.2f)
                lineToRelative(-112.9f, -0.2f)
                curveToRelative(-83.5f, -0.1f, -114.4f, 0.1f, -118.7f, 1f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(447f, 146.8f)
                curveToRelative(-3.6f, 1.8f, -48.5f, 37.3f, -50.7f, 40f)
                curveToRelative(-1f, 1.2f, -1.3f, 16.6f, -1.3f, 69.1f)
                curveToRelative(0f, 37.2f, 0.4f, 68.1f, 0.8f, 68.8f)
                curveToRelative(0.4f, 0.6f, 11.7f, 9.9f, 25.2f, 20.7f)
                curveToRelative(24.9f, 20f, 29f, 22.4f, 34.9f, 21.1f)
                curveToRelative(4.2f, -0.9f, 8.9f, -4.2f, 11.1f, -7.7f)
                curveToRelative(2f, -3.2f, 2f, -5.3f, 2f, -102.9f)
                lineToRelative(0f, -99.6f)
                lineToRelative(-2.3f, -3.4f)
                curveToRelative(-2.4f, -3.7f, -9.7f, -7.9f, -13.7f, -7.9f)
                curveToRelative(-1.4f, -0f, -4.1f, 0.8f, -6f, 1.8f)
                close()
            }
        }.build()

        return _TrainingSelectedIcon!!
    }

@Suppress("ObjectPropertyName")
private var _TrainingSelectedIcon: ImageVector? = null
