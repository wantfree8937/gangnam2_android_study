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

                _state.update { it.copy(isNetworkAvailable = isConnected) }

                if (!isConnected) {
                    _event.emit(TitleEvent.NetworkLost)
                } else if (!wasConnected && isConnected) {
                    _event.emit(TitleEvent.NetworkRestored)
                }
            }
        }
    }
}
