package com.example.sofuser.repository

import com.example.sofuser.template.BaseException

interface ExceptionRepository {

    fun generateApiException(throwable: Throwable): BaseException

    fun generateSOFUserException(exception: BaseException): BaseException
}