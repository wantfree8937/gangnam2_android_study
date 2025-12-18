package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetHomeRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetSavedRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.ToggleBookmarkUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetHomeRecipesUseCase(
        recipeRepository: RecipeRepository,
        bookmarkRepository: BookmarkRepository,
        chefRepository: com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ChefRepository
    ): GetHomeRecipesUseCase {
        return GetHomeRecipesUseCase(recipeRepository, bookmarkRepository, chefRepository)
    }

    @Provides
    @Singleton
    fun provideToggleBookmarkUseCase(bookmarkRepository: BookmarkRepository): ToggleBookmarkUseCase {
        return ToggleBookmarkUseCase(bookmarkRepository)
    }

    @Provides
    @Singleton
    fun provideGetSavedRecipesUseCase(bookmarkRepository: BookmarkRepository): GetSavedRecipesUseCase {
        return GetSavedRecipesUseCase(bookmarkRepository)
    }

    @Provides
    @Singleton
    fun provideGetRecipeDetailsUseCase(
        recipeRepository: RecipeRepository,
        ingredientRepository: IngredientRepository,
        procedureRepository: ProcedureRepository
    ): GetRecipeDetailsUseCase {
        return GetRecipeDetailsUseCase(recipeRepository, ingredientRepository, procedureRepository)
    }
}