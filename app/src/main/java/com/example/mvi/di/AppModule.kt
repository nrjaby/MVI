package com.example.mvi.di

import com.example.mvi.data.ApiService
import com.example.mvi.data.UserRepositoryImp
import com.example.mvi.domain.FetchUsersUseCase
import com.example.mvi.domain.UserRepository
import com.example.mvi.presenter.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    // Retrofit
    single {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

   // Repository
    single<UserRepository> { UserRepositoryImp(get()) }

    // Use Case
    factory { FetchUsersUseCase(get()) }

    // ViewModel
    viewModel { UserViewModel(get()) }
}
