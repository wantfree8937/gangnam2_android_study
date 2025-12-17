package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.BookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.IngredientRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.ProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.use_case.GetSavedRecipesUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class AppApplication : Application() {

    val getSavedRecipesUseCase: GetSavedRecipesUseCase by lazy {
        GetSavedRecipesUseCase(bookmarkRepository)
    }

    val getRecipeDetailsUseCase: GetRecipeDetailsUseCase by lazy {
        GetRecipeDetailsUseCase(recipeRepository, ingredientRepository, procedureRepository)
    }

    val recipeRepository: RecipeRepository by lazy {
        RecipeRepositoryImpl(RecipeDataSourceImpl())
    }

    val bookmarkRepository: BookmarkRepository by lazy {
        BookmarkRepositoryImpl(RecipeDataSourceImpl())
    }

    val ingredientRepository: IngredientRepository by lazy {
        IngredientRepositoryImpl(RecipeDataSourceImpl())
    }

    val procedureRepository: ProcedureRepository by lazy {
        ProcedureRepositoryImpl()
    }

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@AppApplication)
//            modules(appModule)
        }
    }

}
