package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

interface RecipeRepository {
    suspend fun getRecipes(): List<Recipe>
    suspend fun getRecipe(id: Int): Recipe?
    suspend fun getIngredient(id: Int): Ingredient?
}
