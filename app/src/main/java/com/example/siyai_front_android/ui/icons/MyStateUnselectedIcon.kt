package com.example.siyai_front_android.ui.icons

import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

val MyStateUnselectedIcon: ImageVector
    get() {
        if (_MyStateUnselectedIcon != null) {
            return _MyStateUnselectedIcon!!
        }
        _MyStateUnselectedIcon = materialIcon(name = "MyStateUnselectedIcon") {
            materialPath {
                moveTo(16.5f, 3.0f)
                curveToRelative(-1.74f, 0.0f, -3.41f, 0.81f, -4.5f, 2.09f)
                curveTo(10.91f, 3.81f, 9.24f, 3.0f, 7.5f, 3.0f)
                curveTo(4.42f, 3.0f, 2.0f, 5.42f, 2.0f, 8.5f)
                curveToRelative(0.0f, 3.78f, 3.4f, 6.86f, 8.55f, 11.54f)
                lineTo(12.0f, 21.35f)
                lineToRelative(1.45f, -1.32f)
                curveTo(18.6f, 15.36f, 22.0f, 12.28f, 22.0f, 8.5f)
                curveTo(22.0f, 5.42f, 19.58f, 3.0f, 16.5f, 3.0f)
                close()
                moveTo(12.1f, 18.55f)
                lineToRelative(-0.1f, 0.1f)
                lineToRelative(-0.1f, -0.1f)
                curveTo(7.14f, 14.24f, 4.0f, 11.39f, 4.0f, 8.5f)
                curveTo(4.0f, 6.5f, 5.5f, 5.0f, 7.5f, 5.0f)
                curveToRelative(1.54f, 0.0f, 3.04f, 0.99f, 3.57f, 2.36f)
                horizontalLineToRelative(1.87f)
                curveTo(13.46f, 5.99f, 14.96f, 5.0f, 16.5f, 5.0f)
                curveToRelative(2.0f, 0.0f, 3.5f, 1.5f, 3.5f, 3.5f)
                curveToRelative(0.0f, 2.89f, -3.14f, 5.74f, -7.9f, 10.05f)
                close()
            }
        }
        return _MyStateUnselectedIcon!!
    }

private var _MyStateUnselectedIcon: ImageVector? = null