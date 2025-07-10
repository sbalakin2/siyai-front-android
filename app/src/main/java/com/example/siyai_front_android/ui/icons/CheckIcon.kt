package com.example.siyai_front_android.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val CheckIcon: ImageVector
    get() {
        if (_CheckIcon != null) {
            return _CheckIcon!!
        }
        _CheckIcon = ImageVector.Builder(
            name = "CheckIcon",
            defaultWidth = 512.dp,
            defaultHeight = 512.dp,
            viewportWidth = 512f,
            viewportHeight = 512f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(460.5f, 73.4f)
                curveToRelative(-10.5f, 3.3f, -10.9f, 3.6f, -144.5f, 137.2f)
                lineToRelative(-131.5f, 131.4f)
                lineToRelative(-59.5f, -59.5f)
                curveToRelative(-55.3f, -55.1f, -60f, -59.6f, -66.2f, -62.5f)
                curveToRelative(-15.9f, -7.4f, -32.8f, -5.1f, -45.4f, 6.3f)
                curveToRelative(-10f, 9f, -14.9f, 23.4f, -12.5f, 36.4f)
                curveToRelative(2.5f, 13.4f, 0.9f, 11.7f, 83.9f, 94.4f)
                curveToRelative(72.6f, 72.4f, 77.1f, 76.7f, 83.2f, 79.4f)
                curveToRelative(7.8f, 3.4f, 18.5f, 4.4f, 25.7f, 2.5f)
                curveToRelative(12.2f, -3.3f, 7f, 1.6f, 162.9f, -154.3f)
                curveToRelative(161.4f, -161.6f, 152.2f, -151.7f, 154.7f, -166f)
                curveToRelative(3.4f, -19.9f, -9.1f, -39.2f, -29.2f, -45.3f)
                curveToRelative(-5.6f, -1.7f, -16.2f, -1.7f, -21.6f, -0f)
                close()
            }
        }.build()

        return _CheckIcon!!
    }

@Suppress("ObjectPropertyName")
private var _CheckIcon: ImageVector? = null
