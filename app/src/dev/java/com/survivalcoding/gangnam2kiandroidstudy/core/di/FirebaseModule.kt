package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        val auth = Firebase.auth
        // Connect to the Firebase Auth emulator for Dev as well
        auth.useEmulator("10.0.2.2", 9099)
        return auth
    }
}
