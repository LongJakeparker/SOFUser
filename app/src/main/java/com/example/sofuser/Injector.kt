package com.example.sofuser

import android.content.Context
import com.example.sofuser.api.ApiClient
import com.example.sofuser.mapper.UserEntityMapper
import com.example.sofuser.mapper.UserEntityMapperImpl
import com.example.sofuser.repository.ExceptionRepository
import com.example.sofuser.repository.ExceptionRepositoryImpl
import com.example.sofuser.repository.UserRepository
import com.example.sofuser.repository.UserRepositoryImpl
import com.example.sofuser.view.MainContract
import com.example.sofuser.view.MainPresenter

class Injector constructor(val context: Context) {
    private var mainPresenter: MainContract.UserActionsListener? = null

    fun provideMainPresenter(): MainContract.UserActionsListener {
        if (mainPresenter == null) {
            mainPresenter = MainPresenter(provideUserRepository())
        }
        return mainPresenter!!
    }

    private fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl(provideApiClient(), provideExceptionRepository(), provideUserEntityMapper())
    }

    private fun provideExceptionRepository(): ExceptionRepository {
        return ExceptionRepositoryImpl(context)
    }

    private fun provideApiClient(): ApiClient {
        return ApiClient(context)
    }

    private fun provideUserEntityMapper(): UserEntityMapper {
        return UserEntityMapperImpl()
    }
}