package com.survivalcoding.gangnam2kiandroidstudy.presentation.search_recipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SearchRecipesRoot(
    onBack: () -> Unit,
    viewModel: SearchRecipesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SearchRecipesScreen(
        state = state,
        onAction = viewModel::onAction,
        onBack = onBack
    )
}
