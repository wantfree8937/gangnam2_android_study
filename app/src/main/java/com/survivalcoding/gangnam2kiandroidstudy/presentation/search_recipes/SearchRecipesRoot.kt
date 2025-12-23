package com.survivalcoding.gangnam2kiandroidstudy.presentation.search_recipes

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SearchRecipesRoot(
    onBack: () -> Unit,
    viewModel: SearchRecipesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    var backHandlingEnabled by remember { mutableStateOf(true) }

    BackHandler(backHandlingEnabled) {
        // 현재 SearchRecipes에서 BackButton을 눌렀을 경우에만 작동
        backHandlingEnabled = false
        viewModel.onAction(SearchRecipesAction.BackButtonClicked)
    }


    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                is SearchRecipesEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                is SearchRecipesEvent.GoBack -> onBack()
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) {
        SearchRecipesScreen(
            state = state,
            onAction = viewModel::onAction,
            paddingValues = it,
        )
    }
}
