package com.example.sofuser

import android.content.Context
import com.example.sofuser.api.ApiClient
import com.example.sofuser.mapper.ReputationHistoryEntityMapper
import com.example.sofuser.mapper.ReputationHistoryEntityMapperImpl
import com.example.sofuser.mapper.UserEntityMapper
import com.example.sofuser.mapper.UserEntityMapperImpl
import com.example.sofuser.repository.*
import com.example.sofuser.view.main.MainContract
import com.example.sofuser.view.main.MainPresenter
import com.example.sofuser.view.reputation_history.ReputationHistoryContract
import com.example.sofuser.view.reputation_history.ReputationHistoryPresenter

class Injector constructor(val context: Context) {
    private var mainPresenter: MainContract.UserActionsListener? = null
    private var reputationHistoryPresenter: ReputationHistoryContract.UserActionsListener? = null

    fun provideMainPresenter(): MainContract.UserActionsListener {
        if (mainPresenter == null) {
            mainPresenter =
                MainPresenter(provideUserRepository())
        }
        return mainPresenter!!
    }

    fun provideReputationHistoryPresenter(): ReputationHistoryContract.UserActionsListener {
        if (reputationHistoryPresenter == null) {
            reputationHistoryPresenter =
                ReputationHistoryPresenter(provideReputationHistoryRepository())
        }
        return reputationHistoryPresenter!!
    }

    private fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl(
            provideApiClient(),
            provideExceptionRepository(),
            provideUserEntityMapper()
        )
    }

    private fun provideReputationHistoryRepository(): ReputationHistoryRepository {
        return ReputationHistoryRepositoryImpl(
            provideApiClient(),
            provideExceptionRepository(),
            provideReputationHistoryEntityMapper()
        )
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

    private fun provideReputationHistoryEntityMapper(): ReputationHistoryEntityMapper {
        return ReputationHistoryEntityMapperImpl()
    }
}