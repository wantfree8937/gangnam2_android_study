package com.survivalcoding.gangnam2kiandroidstudy.practice3.repository

import com.survivalcoding.gangnam2kiandroidstudy.practice3.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.practice3.model.Recipe

interface RecipeRepository {
    suspend fun getRecipes(): List<Recipe>
    suspend fun getRecipe(id: Int): Recipe?
    suspend fun getIngredient(id: Int): Ingredient?
}
