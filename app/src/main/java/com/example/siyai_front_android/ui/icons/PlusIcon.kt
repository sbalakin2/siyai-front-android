package com.example.siyai_front_android.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val PlusIcon: ImageVector
    get() {
        if (_PlusIcon != null) {
            return _PlusIcon!!
        }
        _PlusIcon = ImageVector.Builder(
            name = "PlusIcon",
            defaultWidth = 512.dp,
            defaultHeight = 512.dp,
            viewportWidth = 512f,
            viewportHeight = 512f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(249.2f, 1.1f)
                curveToRelative(-4.5f, 1.3f, -10.8f, 8f, -12.1f, 12.6f)
                curveToRelative(-0.7f, 2.6f, -1.1f, 39.2f, -1.1f, 113f)
                lineToRelative(0f, 109.3f)
                lineToRelative(-111.3f, 0.2f)
                lineToRelative(-111.3f, 0.3f)
                lineToRelative(-4.1f, 2.7f)
                curveToRelative(-6.3f, 4.1f, -8.8f, 9f, -8.8f, 16.9f)
                curveToRelative(0f, 5.4f, 0.5f, 7.2f, 2.7f, 10.6f)
                curveToRelative(1.5f, 2.2f, 4.4f, 5.1f, 6.5f, 6.4f)
                lineToRelative(3.8f, 2.4f)
                lineToRelative(111.2f, 0.3f)
                lineToRelative(111.3f, 0.2f)
                lineToRelative(0.2f, 111.3f)
                lineToRelative(0.3f, 111.2f)
                lineToRelative(2.4f, 3.8f)
                curveToRelative(1.3f, 2.1f, 4.2f, 5f, 6.4f, 6.5f)
                curveToRelative(3.4f, 2.3f, 5.2f, 2.7f, 10.7f, 2.7f)
                curveToRelative(5.5f, -0f, 7.3f, -0.4f, 10.7f, -2.7f)
                curveToRelative(2.2f, -1.5f, 5.1f, -4.4f, 6.4f, -6.5f)
                lineToRelative(2.4f, -3.8f)
                lineToRelative(0.3f, -111.2f)
                lineToRelative(0.2f, -111.3f)
                lineToRelative(111.3f, -0.2f)
                lineToRelative(111.2f, -0.3f)
                lineToRelative(3.8f, -2.4f)
                curveToRelative(2.1f, -1.3f, 5f, -4.2f, 6.5f, -6.4f)
                curveToRelative(2.3f, -3.4f, 2.7f, -5.2f, 2.7f, -10.7f)
                curveToRelative(0f, -5.5f, -0.4f, -7.3f, -2.7f, -10.7f)
                curveToRelative(-1.5f, -2.2f, -4.4f, -5.1f, -6.5f, -6.4f)
                lineToRelative(-3.8f, -2.4f)
                lineToRelative(-111.2f, -0.3f)
                lineToRelative(-111.3f, -0.2f)
                lineToRelative(-0.2f, -111.3f)
                lineToRelative(-0.3f, -111.2f)
                lineToRelative(-2.4f, -3.8f)
                curveToRelative(-4.7f, -7.6f, -15.1f, -11.3f, -23.9f, -8.6f)
                close()
            }
        }.build()

        return _PlusIcon!!
    }

@Suppress("ObjectPropertyName")
private var _PlusIcon: ImageVector? = null
