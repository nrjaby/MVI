package com.example.mvi.domain

class FetchUsersUseCase(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.getUsers()
}