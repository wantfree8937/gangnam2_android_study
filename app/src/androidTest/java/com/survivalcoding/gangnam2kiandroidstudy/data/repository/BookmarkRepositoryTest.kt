package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.local.AppDatabase
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.local.RecipeDao
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookmarkRepositoryTest {

    private lateinit var database: AppDatabase
    private lateinit var recipeDao: RecipeDao
    private lateinit var repository: BookmarkRepository

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        recipeDao = database.recipeDao()
        repository = BookmarkRepositoryImpl(recipeDao)
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun writeUserAndReadInList() = runTest {
        val recipe = Recipe(
            id = 1,
            category = "Italian",
            name = "Pasta",
            image = "url",
            chef = "Mario",
            time = "30min",
            rating = 4.5,
            ingredients = emptyList()
        )

        repository.addBookmark(recipe)

        val bookmarks = repository.getBookmarks()
        assertEquals(1, bookmarks.size)
        assertEquals(recipe.id, bookmarks[0].id)
        assertTrue(repository.isBookmarked(recipe.id))
    }

    @Test
    fun removeBookmark() = runTest {
        val recipe = Recipe(
            id = 1,
            category = "Italian",
            name = "Pasta",
            image = "url",
            chef = "Mario",
            time = "30min",
            rating = 4.5,
            ingredients = emptyList()
        )

        repository.addBookmark(recipe)
        repository.removeBookmark(recipe)

        val bookmarks = repository.getBookmarks()
        assertEquals(0, bookmarks.size)
        assertFalse(repository.isBookmarked(recipe.id))
    }
}
