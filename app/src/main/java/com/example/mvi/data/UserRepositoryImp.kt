package com.example.mvi.data

import com.example.mvi.domain.UserDomain
import com.example.mvi.domain.UserRepository

class UserRepositoryImp(private val apiService: ApiService) : UserRepository {

    override suspend fun getUsers(): List<UserDomain> {
        return apiService.getUsers().map { user ->
            UserDomain(id = user.id, name = user.name, email = user.email)
        }
    }
}