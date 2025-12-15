package com.survivalcoding.gangnam2kiandroidstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.NavigationRoot
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.Gangnam2kiAndroidStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Gangnam2kiAndroidStudyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationRoot()
                }
            }
        }
    }
}
