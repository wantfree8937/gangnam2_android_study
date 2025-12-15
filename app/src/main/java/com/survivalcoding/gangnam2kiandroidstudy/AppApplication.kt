package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl

class AppApplication : Application() {

    val recipeRepository: RecipeRepository by lazy {
        RecipeRepositoryImpl(RecipeDataSourceImpl())
    }
}
