package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.IngredientRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRecipeRepository(dataSource: RecipeDataSource): RecipeRepository {
        return RecipeRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideBookmarkRepository(dataSource: RecipeDataSource): BookmarkRepository {
        return BookmarkRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideIngredientRepository(dataSource: RecipeDataSource): IngredientRepository {
        return IngredientRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideProcedureRepository(): ProcedureRepository {
        return ProcedureRepositoryImpl()
    }
}