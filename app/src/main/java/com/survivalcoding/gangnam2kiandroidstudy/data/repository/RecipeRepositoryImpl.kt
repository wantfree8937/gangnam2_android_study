package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepositoryImpl @Inject constructor(
    private val dataSource: RecipeDataSource
) : RecipeRepository {

    override suspend fun getRecipes(): List<Recipe> = withContext(Dispatchers.IO) {
        dataSource.getRecipes()
    }

    override suspend fun getRecipe(id: Int): Recipe = withContext(Dispatchers.IO) {
        val recipes = dataSource.getRecipes()
        recipes.find { it.id == id } ?: throw IllegalStateException("Recipe with id $id not found")
    }
}