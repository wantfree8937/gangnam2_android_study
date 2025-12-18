package com.survivalcoding.gangnam2kiandroidstudy.domain.use_case

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import javax.inject.Inject

class ToggleBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend fun execute(recipe: Recipe) {
        if (bookmarkRepository.isBookmarked(recipe.id)) {
            bookmarkRepository.removeBookmark(recipe)
        } else {
            bookmarkRepository.addBookmark(recipe)
        }
    }
}
