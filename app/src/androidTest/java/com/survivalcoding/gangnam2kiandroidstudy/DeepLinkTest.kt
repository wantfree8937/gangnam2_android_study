package com.survivalcoding.gangnam2kiandroidstudy

import android.content.Intent
import android.net.Uri
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class DeepLinkTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testSavedRecipesDeepLink() {
        // Given: myapp://recipes/saved 딥링크 인텐트 준비
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("myapp://recipes/saved")).apply {
            `package` = "com.survivalcoding.gangnam2kiandroidstudy"
        }

        // When: 인텐트를 통해 Activity에 딥링크 전달
        composeTestRule.runOnUiThread {
            composeTestRule.activity.onNewIntent(intent)
        }

        // UI 갱신 대기
        composeTestRule.waitForIdle()

        // Then: "Saved recipes" 텍스트가 화면에 나타날 때까지 대기 후 확인 (최대 5초)
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("Saved recipes").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithText("Saved recipes").assertIsDisplayed()
    }

    @Test
    fun testRecipeDetailDeepLink() {
        // Given: myapp://recipes/detail/1 딥링크 인텐트 준비
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("myapp://recipes/detail/1")).apply {
            `package` = "com.survivalcoding.gangnam2kiandroidstudy"
        }

        // When: 인텐트 전달
        composeTestRule.runOnUiThread {
            composeTestRule.activity.onNewIntent(intent)
        }

        // UI 갱신 대기
        composeTestRule.waitForIdle()

        // Then: 상세 화면의 "Ingredient" 탭이 나타날 때까지 대기 후 확인 (최대 5초)
        // 데이터 로딩 시간이 있을 수 있으므로 waitUntil 필수
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithText("Ingredient").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithText("Ingredient").assertIsDisplayed()
    }
}
