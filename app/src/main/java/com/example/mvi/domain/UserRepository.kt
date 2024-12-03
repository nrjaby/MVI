package com.example.mvi.domain

interface UserRepository {

    suspend fun getUsers(): List<UserDomain>

}