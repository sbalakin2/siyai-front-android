package com.example.siyai_front_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.siyai_front_android.presentation.siyai_container.SiyaiContainer
import com.example.siyai_front_android.ui.theme.SiyaifrontandroidTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as SiyAiApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SiyaifrontandroidTheme {
                SiyaiContainer(
                    viewModelFactory = viewModelFactory
                )
            }
        }
    }
}