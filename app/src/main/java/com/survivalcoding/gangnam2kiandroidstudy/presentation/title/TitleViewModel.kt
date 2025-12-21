package com.survivalcoding.gangnam2kiandroidstudy.presentation.title

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.network.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TitleViewModel @Inject constructor(
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _state = MutableStateFlow(TitleState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<TitleEvent>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val event = _event.asSharedFlow()

    init {
        observeNetwork()
    }

    private fun observeNetwork() {
        viewModelScope.launch {
            networkMonitor.isOnline.collect { isConnected ->
                val wasConnected = _state.value.isNetworkAvailable

                // UI 상태 업데이트
                _state.update { it.copy(isNetworkAvailable = isConnected) }

                // 이벤트 전송
                if (!isConnected) {
                    _event.emit(TitleEvent.ShowSnackbar("네트워크 연결이 끊겼습니다 😢"))
                } else if (!wasConnected && isConnected) {
                    _event.emit(TitleEvent.ShowSnackbar("네트워크가 다시 연결되었습니다 🚀"))
                }
            }
        }
    }
}