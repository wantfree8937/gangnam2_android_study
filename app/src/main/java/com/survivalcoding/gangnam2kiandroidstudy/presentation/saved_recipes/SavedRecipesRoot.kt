package com.survivalcoding.gangnam2kiandroidstudy.presentation.saved_recipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedRecipesRoot(
    onRecipeClick: (Int) -> Unit,
    viewModel: SavedRecipesViewModel = koinViewModel()
) {
    val recipes by viewModel.recipes.collectAsState()

    SavedRecipesScreen(
        recipes = recipes,
        onRecipeClick = onRecipeClick,
        onBookmarkClick = viewModel::toggleBookmark
    )
}
