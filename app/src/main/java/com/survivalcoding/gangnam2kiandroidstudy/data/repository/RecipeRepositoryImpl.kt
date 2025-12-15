package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepositoryImpl(
    private val dataSource: RecipeDataSource
) : RecipeRepository {

    override suspend fun getRecipes(): List<Recipe> = withContext(Dispatchers.IO) {
        dataSource.getRecipes()
    }

    override suspend fun getRecipe(id: Int): Recipe? = withContext(Dispatchers.IO) {
        val recipes = dataSource.getRecipes()
        recipes.find { it.id == id }
    }

    override suspend fun getIngredient(id: Int): Ingredient? = withContext(Dispatchers.IO) {
        val recipes = dataSource.getRecipes()
        recipes
            .flatMap { it.ingredients }
            .find { it.ingredient.id == id }
            ?.ingredient
    }
}
