package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class InputFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun inputFieldTest() {
        var changedText = ""
        val expectedText = "Hello Compose"

        composeTestRule.setContent {
            InputField(
                value = "",
                onValueChange = { newText ->
                    changedText = newText
                },
                label = "Label",
                placeholder = "Placeholder"
            )
        }

        composeTestRule
            .onNodeWithText("Placeholder")
            .performTextInput(expectedText)

        assertEquals(expectedText, changedText)
    }
}
