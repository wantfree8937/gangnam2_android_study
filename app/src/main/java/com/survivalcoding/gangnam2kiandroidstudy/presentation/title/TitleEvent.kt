package com.survivalcoding.gangnam2kiandroidstudy.presentation.title

sealed interface TitleEvent {
    data class ShowSnackbar(val message: String) : TitleEvent
}