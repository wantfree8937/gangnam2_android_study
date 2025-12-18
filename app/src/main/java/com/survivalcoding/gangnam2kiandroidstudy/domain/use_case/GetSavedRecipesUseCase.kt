package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import javax.inject.Inject

class GetSavedRecipesUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    suspend fun execute(): List<Recipe> {
        return repository.getBookmarks()
    }

    suspend fun toggleBookmark(recipe: Recipe) {
        if (repository.isBookmarked(recipe.id)) {
            repository.removeBookmark(recipe)
        } else {
            repository.addBookmark(recipe)
        }
    }
}
