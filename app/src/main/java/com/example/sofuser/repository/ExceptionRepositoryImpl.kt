package com.example.sofuser.repository

import android.content.Context
import com.example.sofuser.R
import com.example.sofuser.template.BaseException
import retrofit2.HttpException
import java.net.ConnectException

class ExceptionRepositoryImpl constructor(private val context: Context): ExceptionRepository {

    enum class SOFUserException constructor(val code: Int) {
        CONNECTION_FAILED(100),
    }

    override fun generateApiException(throwable: Throwable): BaseException {
        val exception = BaseException()
        when {
            throwable is HttpException -> {
                exception.code = throwable.code()
                exception.errorMessage = throwable.message()
            }
            throwable.cause is ConnectException -> {
                exception.code = SOFUserException.CONNECTION_FAILED.code
                exception.errorMessage = context.getString(R.string.message_network_connection_failed)
            }
            else -> {
                exception.errorMessage = throwable.localizedMessage!!
            }
        }
        return exception
    }

    override fun generateSOFUserException(exception: BaseException): BaseException {
        when (exception.code) {

        }
        return exception
    }

}