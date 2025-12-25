package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class RecipeCard2Test {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun recipeCard2_renders_correctly() {
        val recipeName = "Test Recipe"
        
        composeTestRule.setContent {
            RecipeCard2(
                recipeName = recipeName,
                time = "10 mins",
                rating = 4.5,
                imageUrl = "",
                isBookmarked = false,
                onCardClick = {},
                onBookmarkClick = {}
            )
        }

        composeTestRule.onNodeWithText(recipeName).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Bookmark").assertIsDisplayed()
    }

    @Test
    fun recipeCard2_bookmark_click_callback() {
        var clicked = false
        
        composeTestRule.setContent {
            RecipeCard2(
                recipeName = "Test Recipe",
                time = "10 mins",
                rating = 4.5,
                imageUrl = "",
                isBookmarked = false,
                onCardClick = {},
                onBookmarkClick = { clicked = true }
            )
        }

        composeTestRule.onNodeWithContentDescription("Bookmark").performClick()
        assertTrue(clicked)
    }
}
