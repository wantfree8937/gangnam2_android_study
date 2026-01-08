package com.survivalcoding.gangnam2kiandroidstudy.core.di


import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.chef.ChefDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.chef.ChefDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.recipe.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.recipe.RecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.local.RecipeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRecipeDataSource(recipeDao: RecipeDao): RecipeDataSource {
        return RecipeDataSourceImpl(recipeDao)
    }

    @Provides
    @Singleton
    fun provideChefDataSource(): ChefDataSource {
        return ChefDataSourceImpl()
    }
}