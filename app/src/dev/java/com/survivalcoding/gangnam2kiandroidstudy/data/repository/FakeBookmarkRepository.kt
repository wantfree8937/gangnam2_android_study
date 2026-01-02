package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class FakeBookmarkRepository @Inject constructor() : BookmarkRepository {

    private val sampleRecipes = listOf(
        Recipe(
            id = 1,
            category = "Indian",
            name = "Traditional Steamed Momos",
            image = "https://images.unsplash.com/photo-1496116218417-1a781b1c416c",
            chef = "By Jega face",
            time = "20 min",
            rating = 4.5,
            ingredients = emptyList()
        ),
        Recipe(
            id = 2,
            category = "Italian",
            name = "Pasta with Garlic and Oil",
            image = "https://images.unsplash.com/photo-1473093226795-af9932fe5856",
            chef = "By Mario",
            time = "15 min",
            rating = 4.0,
            ingredients = emptyList()
        ),
        Recipe(
            id = 3,
            category = "Asian",
            name = "Beef Stir Fry",
            image = "https://images.unsplash.com/photo-1512058560366-cd242d45a003",
            chef = "By Chef Lee",
            time = "25 min",
            rating = 4.8,
            ingredients = emptyList()
        )
    )

    private val _bookmarks = MutableStateFlow<List<Recipe>>(sampleRecipes)

    override fun getBookmarksFlow(): Flow<List<Recipe>> {
        return _bookmarks.asStateFlow()
    }

    override suspend fun getBookmarks(): List<Recipe> {
        return _bookmarks.value
    }

    override suspend fun addBookmark(recipe: Recipe) {
        _bookmarks.update { currentList ->
            if (currentList.none { it.id == recipe.id }) {
                currentList + recipe
            } else {
                currentList
            }
        }
    }

    override suspend fun removeBookmark(recipe: Recipe) {
        _bookmarks.update { currentList ->
            currentList.filter { it.id != recipe.id }
        }
    }

    override suspend fun isBookmarked(id: Int): Boolean {
        return _bookmarks.value.any { it.id == id }
    }
}
