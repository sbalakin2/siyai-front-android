package com.example.siyai_front_android.presentation.main.bottom_nav_container

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.siyai_front_android.presentation.audio_player.AudioPlayerScreen
import com.example.siyai_front_android.presentation.free_lesson_detail.FreeLessonDetailScreen
import com.example.siyai_front_android.presentation.free_lessons.FreeLessonsScreen
import com.example.siyai_front_android.presentation.main.home_container.navigation.HomeContainer
import com.example.siyai_front_android.presentation.model.Product
import com.example.siyai_front_android.presentation.my_state.calendar_with_info.CalendarWithInfoScreen
import com.example.siyai_front_android.presentation.my_state.select_cycles.edit_cycles.EditCyclesScreen
import com.example.siyai_front_android.presentation.my_state.main.MyStateScreen
import com.example.siyai_front_android.presentation.my_state.select_cycles.select_last_3_cycles.SelectLast3CyclesScreen
import com.example.siyai_front_android.presentation.product_detail.ProductDetailScreen
import com.example.siyai_front_android.presentation.profile.ProfileScreen
import com.example.siyai_front_android.presentation.profile_editing.ProfileEditingScreen
import com.example.siyai_front_android.presentation.sign_day.SignOfTheDayScreen
import com.example.siyai_front_android.presentation.video_player.VideoPlayerScreen


@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory,
    exitFromApp: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = MainRoute.Home,
        modifier = modifier,
    ) {
        composable<MainRoute.Home> {
            HomeContainer(
                viewModelFactory = viewModelFactory,
                navigateToSignOfTheDayScreen = {
                    navController.navigate(MainRoute.SignOfTheDay)
                },
                navigateToProductDetailScreen = { product ->
                    navController.navigate(
                        MainRoute.ProductDetail(product.imageId, product.name, product.price)
                    )
                },
                navigateToFreeLessonsScreen = {
                     navController.navigate(MainRoute.FreeLessons)
                }
            )
        }
        composable<MainRoute.Training> {}
        composable<MainRoute.Audio> {}
        composable<MainRoute.MyState>{
            MyStateScreen(
                onContinueClick = { navController.navigate(MainRoute.Last3Cycles) },
                onCalendarClick = { navController.navigate(MainRoute.CalendarWithInfo) },
                viewModelFactory = viewModelFactory
            )
        }
        composable<MainRoute.Last3Cycles> {
            SelectLast3CyclesScreen(
                onBackClick = { navController.popBackStack() },
                onContinueClick = { navController.navigate(MainRoute.CalendarWithInfo) },
                viewmodelFactory = viewModelFactory
            )
        }
        composable<MainRoute.CalendarWithInfo> {
            CalendarWithInfoScreen(
                onEditClick = {
                    navController.navigate(MainRoute.EditCycles)
                },
                onBackClick = { navController.popBackStack() },
                viewmodelFactory = viewModelFactory
            )
        }
        composable<MainRoute.EditCycles> {
            EditCyclesScreen(
                onSaveCycles = { navController.navigate(MainRoute.MyState) },
                onBackClick = { navController.popBackStack() },
                onDeleteAllCycles = { navController.navigate(MainRoute.MyState) },
                viewmodelFactory = viewModelFactory,
            )
        }
        composable<MainRoute.Profile> {
            ProfileScreen(
                onEditClick = { email, firstName, lastName, birthday, country, city ->
                    navController.navigate(
                        MainRoute.ProfileEditing(
                            email = email,
                            firstName = firstName,
                            lastName = lastName,
                            birthday = birthday,
                            country = country,
                            city = city
                        )
                    )
                },
                viewModelFactory = viewModelFactory,
                onExitClick = exitFromApp
            )
        }
        composable<MainRoute.ProfileEditing> {
            ProfileEditingScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onOnboardingClick = {},
                onDeleteProfile = exitFromApp,
                viewModelFactory = viewModelFactory
            )
        }
        composable<MainRoute.SignOfTheDay> {
            SignOfTheDayScreen(
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
        composable<MainRoute.ProductDetail> {
            val item = it.toRoute<MainRoute.ProductDetail>()
            ProductDetailScreen(
                product = Product(item.imageId, item.name, item.price),
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
        composable<MainRoute.FreeLessons> {
            FreeLessonsScreen(
                onBackClicked = {
                    navController.popBackStack()
                },
                navigateToFreeLessonDetail = {
                    navController.navigate(
                        MainRoute.FreeLessonDetail
                    )
                }
            )
        }
        composable<MainRoute.FreeLessonDetail> {
            FreeLessonDetailScreen(
                onBackClicked = {
                    navController.popBackStack()
                },
                navigateToAudioPlayScreen = {
                    navController.navigate(
                        MainRoute.AudioPlay
                    )
                },
                navigateToVideoPlayScreen = {
                    navController.navigate(
                        MainRoute.VideoPlay
                    )
                }
            )
        }
        composable<MainRoute.AudioPlay> {
            AudioPlayerScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable<MainRoute.VideoPlay> {
            VideoPlayerScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
