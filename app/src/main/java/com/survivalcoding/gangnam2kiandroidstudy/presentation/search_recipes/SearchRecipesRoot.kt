package com.survivalcoding.gangnam2kiandroidstudy.presentation.search_recipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SearchRecipesRoot(
    viewModel: SearchRecipesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var showBottomSheet by remember { mutableStateOf(false) }

    SearchRecipesScreen(
        state = state,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        onFilterChanged = viewModel::onFilterChanged,
        onShowFilter = { showBottomSheet = true },
        showBottomSheet = showBottomSheet,
        onDismissBottomSheet = { showBottomSheet = false }
    )
}
