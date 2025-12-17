package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.data_source.RecipeDataSourceImpl
import org.koin.dsl.module

val databaseMoudle = module {
    single<RecipeDataSource> { RecipeDataSourceImpl() }
}