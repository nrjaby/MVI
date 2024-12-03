package com.example.mvi.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi.domain.FetchUsersUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class UserViewModel(private val fetchUsersUseCase: FetchUsersUseCase) : ViewModel() {

    val userIntent = Channel<UserIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<UserState>(UserState.Idle)
    val stateFlow: StateFlow<UserState> = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
       viewModelScope.launch {
           userIntent.consumeAsFlow().collect {
               when(it) {
                   is UserIntent.FetchUsers -> fetchUsers()
               }
           }
       }
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _state.value = UserState.Loading
            try {
                val users = fetchUsersUseCase()
                _state.value = UserState.Users(users)
            } catch (e: Exception) {
                _state.value = UserState.Error(e.message ?: "Unknown Error")
            }
        }
    }


}