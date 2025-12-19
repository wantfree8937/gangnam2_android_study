package com.survivalcoding.gangnam2kiandroidstudy.core.network

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DemoNetworkMonitor @Inject constructor() : NetworkMonitor {
    // 4초마다 연결/비연결 상태 자동 전환 (테스트용)
    override val isOnline: Flow<Boolean> = flow {
        while (true) {
            emit(true)
            delay(4000)
            emit(false)
            delay(4000)
        }
    }
}