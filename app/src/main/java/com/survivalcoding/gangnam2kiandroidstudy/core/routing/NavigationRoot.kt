package com.survivalcoding.gangnam2kiandroidstudy.core.routing

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.presentation.home.HomeRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ingredient.IngredientRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.main.MainScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.saved_recipes.SavedRecipesRoot
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
                    backStack = topLevelBackStack
                )
            }

            entry<Route.SignUp> {
                SignUpScreen(
                    backStack = topLevelBackStack,
                )
            }

            entry<Route.Main> {
                val tabBackStack = rememberNavBackStack(Route.Home)

                MainScreen(
                    backStack = tabBackStack,
                    onTabSelected = {
                        if (tabBackStack.last() != it) {
                            tabBackStack.clear()
                            tabBackStack.add(it)
                        }
                    },
                    body = {
                        NavDisplay(
                            modifier = it,
                            backStack = tabBackStack,
                            entryProvider = entryProvider {
                                entry<Route.Home> {
                                    HomeRoot()
                                }
                                entry<Route.SavedRecipes> {
                                    SavedRecipesRoot(
                                        onRecipeClick = { recipeId ->
                                            topLevelBackStack.add(Route.RecipeDetail(recipeId))
                                        }
                                    )
                                }
                            }
                        )
                    }
                )
            }
            entry<Route.RecipeDetail> { navKey ->
                IngredientRoot(
                    recipeId = navKey.recipeId,
                    onBack = { topLevelBackStack.removeLast() }
                )
            }
        }
    )
}
