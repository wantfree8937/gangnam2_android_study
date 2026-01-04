package com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onEmailChange(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun signIn() {
        if (state.value.isLoading) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            val result = authRepository.signInWithEmail(
                state.value.email,
                state.value.password
            )
            result.onSuccess {
                _state.update { it.copy(isLoading = false, isSignInSuccess = true) }
            }.onFailure { e ->
                _state.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            val result = authRepository.signInWithGoogle(idToken)
            result.onSuccess {
                _state.update { it.copy(isLoading = false, isSignInSuccess = true) }
            }.onFailure { e ->
                _state.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}

data class SignInState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isSignInSuccess: Boolean = false,
    val error: String? = null
)
