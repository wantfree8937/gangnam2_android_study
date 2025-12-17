package com.survivalcoding.gangnam2kiandroidstudy.presentation.saved_recipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun SavedRecipesRoot(
    onRecipeClick: (Int) -> Unit,
    viewModel: SavedRecipesViewModel = hiltViewModel()
) {
    val recipes by viewModel.recipes.collectAsState()

    SavedRecipesScreen(
        recipes = recipes,
        onRecipeClick = onRecipeClick,
        onBookmarkClick = viewModel::toggleBookmark
    )
}
