package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: Flow<User?>
    
    suspend fun signInWithGoogle(idToken: String): Result<User>
    suspend fun signInWithEmail(email: String, password: String): Result<User>
    suspend fun signUpWithEmail(email: String, password: String): Result<User>
    suspend fun signOut()
}
