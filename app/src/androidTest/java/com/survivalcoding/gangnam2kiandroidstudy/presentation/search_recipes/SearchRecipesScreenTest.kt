package com.survivalcoding.gangnam2kiandroidstudy.presentation.search_recipes

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FakeRecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchState
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SearchRecipesScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var viewModel: SearchRecipesViewModel
    private lateinit var fakeRepository: FakeRecipeRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() = runTest {
        hiltRule.inject()
        fakeRepository = FakeRecipeRepository()
        viewModel = SearchRecipesViewModel(fakeRepository)

        // Set content and wait for initial loading to complete
        composeTestRule.setContent {
            val state by viewModel.state.collectAsStateWithLifecycle()
            SearchRecipesScreen(
                state = state,
                onAction = viewModel::onAction,
                paddingValues = PaddingValues(0.dp)
            )
        }
        // Wait for initial data loading in the ViewModel's init block
        // to ensure the UI has the initial recipes before tests run.
        composeTestRule.waitUntil(timeoutMillis = 5000) { !viewModel.state.value.isLoading }
        composeTestRule.waitForIdle()
    }

    @Test
    fun searchRecipe_displaysFilteredResults() = runTest {
        // Initial state: all fake recipes should be displayed
        fakeRepository.getRecipes().forEach { recipe ->
            composeTestRule.onNodeWithText(recipe.name).assertExists()
        }

        // Perform search
        val searchQuery = "lamb"
        composeTestRule.onNodeWithContentDescription("Search recipe").performTextInput(searchQuery)
        composeTestRule.waitForIdle()

        // Verify filtered results
        composeTestRule.onNodeWithText("Lamb chops with fruity couscous and mint").assertExists()
        composeTestRule.onNodeWithText("Traditional spare ribs baked").assertDoesNotExist()
    }

    @Test
    fun applyFilter_displaysFilteredResults() = runTest {
        // Initial state: all fake recipes should be displayed
        fakeRepository.getRecipes().forEach { recipe ->
            composeTestRule.onNodeWithText(recipe.name).assertExists()
        }

        // Show filter sheet
        composeTestRule.onNodeWithContentDescription("Filter").performClick()
        composeTestRule.waitForIdle()

        // Apply a filter (e.g., category "Meat")
        viewModel.onAction(SearchRecipesAction.ApplyFilters(FilterSearchState(selectedCategory = "Meat")))
        composeTestRule.waitForIdle()

        // Verify filtered results
        composeTestRule.onNodeWithText("Lamb chops with fruity couscous and mint").assertExists()
        composeTestRule.onNodeWithText("Traditional spare ribs baked").assertExists()
        composeTestRule.onNodeWithText("Spice roasted chicken with flavored rice").assertDoesNotExist()
    }
}
