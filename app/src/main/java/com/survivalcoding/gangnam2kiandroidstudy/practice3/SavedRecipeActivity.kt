package com.survivalcoding.gangnam2kiandroidstudy.practice3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.survivalcoding.gangnam2kiandroidstudy.practice4.presentation.search_recipes.SearchRecipesScreen
import com.survivalcoding.gangnam2kiandroidstudy.ui.theme.Gangnam2kiAndroidStudyTheme

class SavedRecipeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Gangnam2kiAndroidStudyTheme {
                SearchRecipesScreen()
            }
        }
    }
}
