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
import com.survivalcoding.gangnam2kiandroidstudy.presentation.search_recipes.SearchRecipesRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_in.SignInScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_up.SignUpScreen
import com.survivalcoding.gangnam2kiandroidstudy.presentation.title.TitleRoot

import androidx.compose.runtime.LaunchedEffect
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    deepLinkDest: Route? = null
) {
    val topLevelBackStack = rememberNavBackStack(Route.Title)

    LaunchedEffect(deepLinkDest) {
        deepLinkDest?.let { dest ->
            when (dest) {
                is Route.Main -> {
                    // 메인 화면으로 이동 (로그인 건너뛰기 등 상황 고려, 여기서는 단순 이동)
                    // 기존 스택을 정리하고 싶다면 clear() 호출
                    if (topLevelBackStack.last() != dest) {
                        topLevelBackStack.clear()
                        topLevelBackStack.add(dest)
                    }
                }
                is Route.RecipeDetail -> {
                    // 상세 화면은 스택에 추가
                    topLevelBackStack.add(dest)
                }
                else -> {
                    // 그 외 경로는 상황에 따라 추가 (여기선 예시로 추가)
                    topLevelBackStack.add(dest)
                }
            }
        }
    }

    NavDisplay(
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        backStack = topLevelBackStack,
        entryProvider = entryProvider {
            entry<Route.Title> {
                TitleRoot(
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

            entry<Route.Main> { navKey ->
                // navKey.initialTab을 사용하여 탭 백스택 초기화
                // deepLink로 들어온 경우 navKey가 업데이트되어 재구성될 것임
                val tabBackStack = rememberNavBackStack(navKey.initialTab)

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
                                    HomeRoot(
                                        onSearchClick = { topLevelBackStack.add(Route.Search) },
                                        onRecipeClick = { recipeId ->
                                            topLevelBackStack.add(Route.RecipeDetail(recipeId))
                                        }
                                    )
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
            entry<Route.Search> {
                SearchRecipesRoot(
                    onBack = { topLevelBackStack.removeLast() },
                    onRecipeClick = { recipeId ->
                        topLevelBackStack.add(Route.RecipeDetail(recipeId))
                    }
                )
            }

            entry<Route.RecipeDetail> { navKey ->
                IngredientRoot(
                    recipeId = navKey.recipeId,
                    onBack = { topLevelBackStack.removeAt(topLevelBackStack.lastIndex) }
                )
            }
        }
    )
}
