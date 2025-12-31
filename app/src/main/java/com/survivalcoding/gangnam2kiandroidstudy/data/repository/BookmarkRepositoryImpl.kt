package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.local.RecipeDao
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.local.RecipeEntity
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao
) : BookmarkRepository {

    override suspend fun getBookmarks(): List<Recipe> {
        return recipeDao.getAllRecipes().map { it.toDomain() }
    }

    override suspend fun addBookmark(recipe: Recipe) {
        recipeDao.insertRecipe(RecipeEntity.fromDomain(recipe))
    }

    override suspend fun removeBookmark(recipe: Recipe) {
        recipeDao.deleteRecipeById(recipe.id)
    }

    override suspend fun isBookmarked(id: Int): Boolean {
        return recipeDao.isBookmarked(id)
    }
}
