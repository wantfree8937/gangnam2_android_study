package com.survivalcoding.gangnam2kiandroidstudy.presentation.title

sealed interface TitleEvent {
    data object NetworkLost : TitleEvent
    data object NetworkRestored : TitleEvent
}