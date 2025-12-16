package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

interface BookmarkRepository {
    suspend fun getBookmarks(): List<Recipe>
    suspend fun addBookmark(recipe: Recipe)
    suspend fun removeBookmark(recipe: Recipe)
    suspend fun isBookmarked(id: Int): Boolean
}
