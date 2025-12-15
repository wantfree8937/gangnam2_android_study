package com.survivalcoding.gangnam2kiandroidstudy.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication

@Composable
fun HomeRoot(
    viewModel: HomeViewModel = viewModel(
        factory = HomeViewModel.factory(
            LocalContext.current.applicationContext as AppApplication
        )
    ),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        state = state,
        onQueryChanged = viewModel::onQueryChanged,
        onSelectCategory = viewModel::onSelectCategory
    )
}
