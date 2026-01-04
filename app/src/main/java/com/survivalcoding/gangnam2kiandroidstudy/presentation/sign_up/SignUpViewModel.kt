package com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_up

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
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    fun onEmailChange(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun onConfirmPasswordChange(password: String) {
        _state.update { it.copy(confirmPassword = password) }
    }

    fun signUp() {
        if (state.value.isLoading) return
        if (state.value.password != state.value.confirmPassword) {
            _state.update { it.copy(error = "Passwords do not match") }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            val result = authRepository.signUpWithEmail(
                state.value.email,
                state.value.password
            )
            result.onSuccess {
                _state.update { it.copy(isLoading = false, isSignUpSuccess = true) }
            }.onFailure { e ->
                _state.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}

data class SignUpState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val isSignUpSuccess: Boolean = false,
    val error: String? = null
)
