package com.survivalcoding.gangnam2kiandroidstudy.presentation.title

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch

@Composable
fun TitleRoot(
    viewModel: TitleViewModel = hiltViewModel(),
    onClickSignIn: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                is TitleEvent.NetworkLost -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "네트워크 연결이 끊겼습니다",
                            duration = SnackbarDuration.Short
                        )
                    }
                }

                is TitleEvent.NetworkRestored -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "네트워크가 다시 연결되었습니다",
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            }
        }
    }

    val scrollState = rememberScrollState()

    TitleScreen(
        state = state,
        scrollState = scrollState,
        snackbarHostState = snackbarHostState,
        onClickSignIn = onClickSignIn
    )
}