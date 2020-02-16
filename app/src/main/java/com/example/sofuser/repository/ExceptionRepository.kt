package com.example.sofuser.repository

import com.example.sofuser.templete.BaseException

interface ExceptionRepository {

    fun generateApiException(throwable: Throwable): BaseException

    fun generateSOFUserException(exception: BaseException): BaseException
}