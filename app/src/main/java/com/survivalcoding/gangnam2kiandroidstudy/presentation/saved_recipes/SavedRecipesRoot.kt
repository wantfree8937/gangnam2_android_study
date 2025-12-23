package com.survivalcoding.gangnam2kiandroidstudy.presentation.saved_recipes

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun SavedRecipesRoot(
    onRecipeClick: (Int) -> Unit,
    viewModel: SavedRecipesViewModel = hiltViewModel()
) {
    val recipes by viewModel.recipes.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow {
            !listState.canScrollForward
        }
            .distinctUntilChanged()
            .filter { it }
            .collect {
                snackbarHostState.showSnackbar("더 이상 스크롤할 수 없습니다.")
            }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) {
        SavedRecipesScreen(
            recipes = recipes,
            onRecipeClick = onRecipeClick,
            onBookmarkClick = viewModel::toggleBookmark,
            listState = listState,
        )
    }
}
