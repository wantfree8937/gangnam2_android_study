package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository

class IngredientRepositoryImpl(
    private val recipeDataSource: RecipeDataSource
): IngredientRepository {
    override suspend fun getIngredients(recipeId: Int): List<Ingredient> {
        val allRecipes = recipeDataSource.getRecipes()
        val recipe = allRecipes.find { it.id == recipeId }
        return recipe?.ingredients?.map { it.ingredient } ?: emptyList()
    }
}
