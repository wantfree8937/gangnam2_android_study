package com.survivalcoding.gangnam2kiandroidstudy.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BottomNavigationBar

@Composable
fun MainScreen(
    body: @Composable (modifier: Modifier) -> Unit,
    backStack: NavBackStack<NavKey>,
    onTabSelected: (NavKey) -> Unit
) {
    val currentKey = backStack.last()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentKey = currentKey,
                onTabSelected = onTabSelected
            )
        }
    ) { innerPadding ->
        body(Modifier.padding(innerPadding))
    }
}
