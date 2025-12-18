package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient

interface IngredientRepository {
    suspend fun getIngredients(recipeId: Int): List<Ingredient>
}
