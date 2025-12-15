package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_in.SignInScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_up.SignUpScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.title.TitleScreen

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
) {
    val topLevelBackStack = rememberNavBackStack(Route.Title)

    NavDisplay(
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = topLevelBackStack,
        entryProvider = entryProvider {
            entry<Route.Title> {
                TitleScreen(
                    onClickSignIn = {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(Route.SignIn)
                    },
                )
            }

            entry<Route.SignIn> {
                SignInScreen(
                    backStack = rememberNavBackStack(Route.SignIn)
                )
            }

            entry<Route.SignUp> {
                SignUpScreen(
                    backStack = rememberNavBackStack(Route.SignUp),
                )
            }
        }
    )
}