package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class TabsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun tabsTest() {
        var selectedTabIndex = 0
        val labels = listOf("label1", "label2")

        val expected1 = 0
        val expected2 = 1

        composeTestRule.setContent {
            Tabs(
                labels = labels,
                selectedTabIndex = 0,
                onTabSelected = { index ->
                    selectedTabIndex = index
                }
            )
        }

        composeTestRule
            .onNodeWithText("label1")
            .performClick()

        assertEquals(expected1, selectedTabIndex)

        composeTestRule
            .onNodeWithText("label2")
            .performClick()

        assertEquals(expected2, selectedTabIndex)
    }
}
