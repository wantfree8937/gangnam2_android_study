package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedRecipesUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    fun execute(): Flow<List<Recipe>> {
        return repository.getBookmarksFlow()
    }

    suspend fun toggleBookmark(recipe: Recipe) {
        if (repository.isBookmarked(recipe.id)) {
            repository.removeBookmark(recipe)
        } else {
            repository.addBookmark(recipe)
        }
    }
}
