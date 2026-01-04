package com.survivalcoding.gangnam2kiandroidstudy.core.di

import android.content.Context
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.recipe.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.chef.ChefDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ClipboardRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.IngredientRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.chef.ChefRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ChefRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ClipboardRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.AuthRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.local.RecipeDao

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
    fun provideIngredientRepository(dataSource: RecipeDataSource): IngredientRepository {
        return IngredientRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideProcedureRepository(): ProcedureRepository {
        return ProcedureRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideChefRepository(dataSource: ChefDataSource): ChefRepository {
        return ChefRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideClipboardRepository(@ApplicationContext context: Context): ClipboardRepository {
        return ClipboardRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }
}