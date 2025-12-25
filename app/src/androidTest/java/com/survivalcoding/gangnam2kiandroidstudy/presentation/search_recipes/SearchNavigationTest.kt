package com.survivalcoding.gangnam2kiandroidstudy.presentation.search_recipes

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FakeClipboardRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FakeIngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FakeProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FakeRecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.CopyLinkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ingredient.IngredientRoot
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ingredient.IngredientViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue

@HiltAndroidTest
class SearchNavigationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var searchViewModel: SearchRecipesViewModel
    private lateinit var ingredientViewModel: IngredientViewModel
    private lateinit var fakeRecipeRepository: FakeRecipeRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        hiltRule.inject()
        fakeRecipeRepository = FakeRecipeRepository()
        searchViewModel = SearchRecipesViewModel(fakeRecipeRepository)
        
        val fakeIngredientRepository = FakeIngredientRepository()
        val fakeProcedureRepository = FakeProcedureRepository()
        val getRecipeDetailsUseCase = GetRecipeDetailsUseCase(
            fakeRecipeRepository,
            fakeIngredientRepository,
            fakeProcedureRepository
        )
        val fakeClipboardRepository = FakeClipboardRepository()
        val copyLinkUseCase = CopyLinkUseCase(fakeClipboardRepository)
        ingredientViewModel = IngredientViewModel(getRecipeDetailsUseCase, copyLinkUseCase)
    }

    @Test
    fun navigateFromSearchToDetail_displaysCorrectRecipe() = runTest {
        composeTestRule.setContent {
            val topLevelBackStack = rememberNavBackStack(Route.Search)
            NavDisplay(
                backStack = topLevelBackStack,
                entryProvider = entryProvider {
                    entry<Route.Search> {
                        val state by searchViewModel.state.collectAsStateWithLifecycle()
                        SearchRecipesScreen(
                            state = state,
                            onAction = { action ->
                                if (action is SearchRecipesAction.RecipeClicked) {
                                    topLevelBackStack.add(Route.RecipeDetail(action.recipeId))
                                } else {
                                    searchViewModel.onAction(action)
                                }
                            },
                            paddingValues = PaddingValues(0.dp)
                        )
                    }
                    entry<Route.RecipeDetail> { navKey ->
                        IngredientRoot(
                            recipeId = navKey.recipeId,
                            onBack = { topLevelBackStack.removeLast() },
                            viewModel = ingredientViewModel
                        )
                    }
                }
            )
        }

        // Wait for search screen to load
        composeTestRule.waitUntil(timeoutMillis = 5000) { !searchViewModel.state.value.isLoading }

        // Find a recipe and click it
        val recipeName = "Traditional spare ribs baked"
        composeTestRule.onNodeWithText(recipeName).performClick()

        // Verify navigation to Detail screen by checking if the recipe name exists in detail screen
        composeTestRule.waitForIdle()
        // In IngredientScreen, the recipe name is displayed in a Text composable
        composeTestRule.onNodeWithText(recipeName).assertExists()
        
        // Check if "Ingredient" tab is visible (specific to IngredientScreen)
        composeTestRule.onNodeWithText("Ingredient").assertExists()
    }
}
