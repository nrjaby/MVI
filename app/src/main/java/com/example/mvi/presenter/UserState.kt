package com.example.mvi.presenter

import com.example.mvi.domain.UserDomain

sealed class UserState {

    object Idle : UserState()
    object Loading : UserState()
    data class Users(val users: List<UserDomain>) : UserState()
    data class Error(val message: String) : UserState()
}