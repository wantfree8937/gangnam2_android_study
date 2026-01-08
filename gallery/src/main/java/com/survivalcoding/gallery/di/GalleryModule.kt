package com.survivalcoding.gallery.di

import android.content.Context
import com.survivalcoding.gallery.data.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GalleryModule {

    @Provides
    @Singleton
    fun providePhotoRepository(@ApplicationContext context: Context): PhotoRepository {
        return PhotoRepository(context)
    }
}
