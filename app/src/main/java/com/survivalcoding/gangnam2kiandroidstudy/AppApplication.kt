package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.survivalcoding.gangnam2kiandroidstudy.practice3.data_source.RecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.practice3.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.practice3.repository.RecipeRepositoryImpl

class AppApplication : Application() {

    val recipeRepository: RecipeRepository by lazy {
        RecipeRepositoryImpl(RecipeDataSourceImpl())
    }
}
