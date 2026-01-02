package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FakeBookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BookmarkRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBookmarkRepository(
        fakeBookmarkRepository: FakeBookmarkRepository
    ): BookmarkRepository
}
