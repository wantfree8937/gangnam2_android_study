package com.survivalcoding.gangnam2kiandroidstudy.practice3.data_source

import com.survivalcoding.gangnam2kiandroidstudy.practice3.model.Recipe

interface RecipeDataSource {
    suspend fun getRecipes(): List<Recipe>
}



