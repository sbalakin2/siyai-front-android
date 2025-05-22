package com.example.siyai_front_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.siyai_front_android.presentation.auth.navigation.AuthRoute
import com.example.siyai_front_android.presentation.siyai_container.SiyaiContainer
import com.example.siyai_front_android.ui.theme.SiyaifrontandroidTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SiyaiViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as SiyAiApplication).appComponent.inject(this)
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        splashScreen.setKeepOnScreenCondition { viewModel.keepSplashScreen.value }

        val startRoute = intent?.data?.toString()?.let { deepLink ->
            if (deepLink.contains("/reset-password/")) {
                val token = deepLink.substringAfterLast("/")
                AuthRoute.PasswordReset(token)
            } else {
                AuthRoute.Onboarding
            }
        } ?: AuthRoute.Onboarding

        setContent {
            SiyaifrontandroidTheme {
                SiyaiContainer(
                    viewModelFactory = viewModelFactory,
                    startDestination = startRoute
                )
            }
        }
    }
}