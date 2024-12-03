package com.example.mvi.presenter

sealed class UserIntent {

    object FetchUsers : UserIntent()
}