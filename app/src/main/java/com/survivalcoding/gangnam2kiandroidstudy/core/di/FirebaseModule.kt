package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.survivalcoding.gangnam2kiandroidstudy.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    private const val EMULATOR_IP = "10.0.2.2"
    private const val AUTH_PORT = 9099
    private const val FIRESTORE_PORT = 8080

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        val auth = Firebase.auth
        if (BuildConfig.FLAVOR == "staging") {
            // 에뮬레이터 사용 시 설정 (안드로이드 에뮬레이터 기준 IP: 10.0.2.2)
            try {
                auth.useEmulator(EMULATOR_IP, AUTH_PORT)
            } catch (e: Exception) {
                // 이미 설정된 경우 등 예외 처리
            }
        }
        return auth
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        val firestore = Firebase.firestore
        if (BuildConfig.FLAVOR == "staging") {
            try {
                firestore.useEmulator(EMULATOR_IP, FIRESTORE_PORT)
            } catch (e: Exception) {
                // 이미 설정된 경우 등 예외 처리
            }
        }
        return firestore
    }
}
