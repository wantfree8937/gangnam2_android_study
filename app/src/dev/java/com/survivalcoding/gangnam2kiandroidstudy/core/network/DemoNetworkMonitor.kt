package com.survivalcoding.gangnam2kiandroidstudy.core.network

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DemoNetworkMonitor @Inject constructor() : NetworkMonitor {
    override val isOnline: Flow<Boolean> = flow {
        delay(4000)
        emit(true)
        delay(4000)
        emit(false)
    }
}
