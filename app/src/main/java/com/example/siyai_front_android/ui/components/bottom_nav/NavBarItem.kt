package com.example.siyai_front_android.ui.components.bottom_nav

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.siyai_front_android.R
import com.example.siyai_front_android.presentation.main.bottom_nav_container.MainRoute
import com.example.siyai_front_android.ui.icons.AudioSelectedIcon
import com.example.siyai_front_android.ui.icons.AudioUnselectedIcon
import com.example.siyai_front_android.ui.icons.HomeSelectedIcon
import com.example.siyai_front_android.ui.icons.HomeUnselectedIcon
import com.example.siyai_front_android.ui.icons.ProfileSelectedIcon
import com.example.siyai_front_android.ui.icons.ProfileUnselectedIcon
import com.example.siyai_front_android.ui.icons.SiyAiIcons
import com.example.siyai_front_android.ui.icons.TrainingSelectedIcon
import com.example.siyai_front_android.ui.icons.TrainingUnselectedIcon

data class NavBarItem(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: Int,
    val route: MainRoute
)

val navBarItems = listOf(
    NavBarItem(
        selectedIcon = SiyAiIcons.HomeSelected,
        unselectedIcon = SiyAiIcons.HomeUnselected,
        label = R.string.home,
        route = MainRoute.Home
    ),
    NavBarItem(
        selectedIcon = SiyAiIcons.TrainingSelected,
        unselectedIcon = SiyAiIcons.TrainingUnselected,
        label = R.string.training,
        route = MainRoute.Training
    ),
    NavBarItem(
        selectedIcon = SiyAiIcons.AudioSelected,
        unselectedIcon = SiyAiIcons.AudioUnselected,
        label = R.string.audio,
        route = MainRoute.Audio
    ),
    NavBarItem(
        selectedIcon = SiyAiIcons.ProfileSelected,
        unselectedIcon = SiyAiIcons.ProfileUnselected,
        label = R.string.profile,
        route = MainRoute.Profile
    ),
)
