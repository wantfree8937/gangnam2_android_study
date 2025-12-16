package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository

class BookmarkRepositoryImpl(
    private val recipeDataSource: RecipeDataSource
) : BookmarkRepository {

    private val bookmarkedIds = mutableSetOf(1, 2, 3, 4, 5)

    override suspend fun getBookmarks(): List<Recipe> {
        val allRecipes = recipeDataSource.getRecipes()
        return allRecipes.filter { it.id in bookmarkedIds }
    }

    override suspend fun addBookmark(recipe: Recipe) {
        bookmarkedIds.add(recipe.id)
    }

    override suspend fun removeBookmark(recipe: Recipe) {
        bookmarkedIds.remove(recipe.id)
    }

    override suspend fun isBookmarked(id: Int): Boolean {
        return bookmarkedIds.contains(id)
    }
}
