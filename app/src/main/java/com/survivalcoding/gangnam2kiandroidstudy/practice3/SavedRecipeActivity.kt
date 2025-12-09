package com.survivalcoding.gangnam2kiandroidstudy.practice3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.survivalcoding.gangnam2kiandroidstudy.practice3.view.GetCookingScreen
import com.survivalcoding.gangnam2kiandroidstudy.practice3.view.SavedRecipesScreen

class SavedRecipeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetCookingScreen()
        }
    }
}
