package com.survivalcoding.gangnam2kiandroidstudy.presentation.saved_recipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication

@Composable
fun SavedRecipesRoot(
    viewModel: SavedRecipesViewModel = viewModel(
        factory = SavedRecipesViewModel.factory(
            LocalContext.current.applicationContext as AppApplication
        )
    )
) {
    val recipes by viewModel.recipes.collectAsState()

    SavedRecipesScreen(recipes = recipes)
}
