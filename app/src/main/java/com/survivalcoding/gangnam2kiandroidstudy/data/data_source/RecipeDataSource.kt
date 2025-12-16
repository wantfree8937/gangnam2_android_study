package com.survivalcoding.gangnam2kiandroidstudy.data.data_source

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

interface RecipeDataSource {
    suspend fun getRecipes(): List<Recipe>
}
