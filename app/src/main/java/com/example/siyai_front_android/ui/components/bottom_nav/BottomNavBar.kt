package com.example.siyai_front_android.ui.components.bottom_nav

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.siyai_front_android.presentation.main.bottom_nav_container.MainRoute
import com.example.siyai_front_android.ui.theme.SiyaifrontandroidTheme

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    selectedItem: MainRoute
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.secondaryContainer
    ) {
        navBarItems.forEach { item ->
            val selected = item.route == selectedItem
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { startRoute ->
                            popUpTo(startRoute) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    val icon = if (selected) item.selectedIcon else item.unselectedIcon
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = icon,
                        contentDescription = stringResource(item.label)
                    )
                },
                label = {
                    Text(stringResource(item.label))
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.outline,
                    unselectedTextColor = MaterialTheme.colorScheme.outline
                )
            )
        }
    }
}

@Composable
@Preview
private fun BottomNavBar_Preview() {
    SiyaifrontandroidTheme {
        BottomNavBar(navController = rememberNavController(), selectedItem = MainRoute.Home)
    }
}