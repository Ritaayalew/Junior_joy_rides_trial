package com.example.trial_junior.feature_junior.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trial_junior.feature_junior.domain.model.UserItem
import com.example.trial_junior.feature_junior.domain.use_case.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(UserState())
    val state = _state.asStateFlow()

    fun registerUser(firstName: String, lastName: String, email: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val user = userUseCases.registerUser(firstName, lastName, email, password)
                _state.value = _state.value.copy(user = user, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message, isLoading = false)
            }
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val token = userUseCases.loginUser(email, password)
                _state.value = _state.value.copy(token = token, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message, isLoading = false)
            }
        }
    }

    fun getMyProfile() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val user = userUseCases.getMyProfile()
                _state.value = _state.value.copy(user = user, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message, isLoading = false)
            }
        }
    }

    fun updateProfile(email: String, firstName: String?, lastName: String?, newEmail: String?, password: String?) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val user = userUseCases.updateProfile(email, firstName, lastName, newEmail, password)
                _state.value = _state.value.copy(user = user, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message, isLoading = false)
            }
        }
    }

    // Method to manually set an error
    fun setError(error: String?) {
        _state.value = _state.value.copy(error = error, isLoading = false)
    }

    // Method to reset the state
    fun resetState() {
        _state.value = UserState()
    }
}

data class UserState(
    val user: UserItem? = null,
    val token: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val role: String = "user" // Default role
)