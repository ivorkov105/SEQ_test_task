package com.example.seq_test_task.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.seq_test_task.data.models.Movie
import com.example.seq_test_task.di.testModule
import com.example.seq_test_task.ui.main.MainActivity
import com.example.seq_test_task.util.FakeMovieRepository
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.KoinTestRule

@RunWith(AndroidJUnit4::class)
class AppNavigationTest: KoinTest {

    @get:Rule(order = 0)
    val koinTestRule = KoinTestRule.create {
        modules(testModule)
    }

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun app_starts_showsMovieList_andNavigatesToDetails() {
        val fakeRepository: FakeMovieRepository = get()

        val testMovies = listOf(
            Movie(1, "Тест Фильм 1", "Test Movie 1", 2025, 8.0, "", "Описание первого фильма", listOf("тест")),
            Movie(2, "Тест Фильм 2", "Test Movie 2", 2026, 9.0, "", "Описание второго фильма", listOf("другой"))
        )
        fakeRepository.setMovies(testMovies)

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithText("Тест Фильм 1")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithText("Тест Фильм 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Тест Фильм 2").assertIsDisplayed()

        composeTestRule.onNodeWithText("Тест Фильм 1").performClick()

        composeTestRule.onRoot().printToLog("DEBUG")

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodes(
                    hasText("Тест Фильм 1") or hasContentDescription("Тест Фильм 1")
                )
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithText("Тест Фильм 2").assertIsNotDisplayed()
    }
}