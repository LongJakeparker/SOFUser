package com.example.sofuser.api

import com.example.sofuser.repository.ExceptionRepository
import com.example.sofuser.templete.BaseException
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCallback<T : Any> constructor(
    private val exceptionRepository: ExceptionRepository,
    private val serviceCallBack: ServiceCallBack<T>
) : Callback<T> {

    override fun onFailure(call: Call<T>, throwable: Throwable) {

        serviceCallBack.onError(exceptionRepository.generateApiException(throwable))
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (!response.isSuccessful) {
            try {
                val exception =
                    Gson().fromJson(response.errorBody()?.string(), BaseException::class.java)
                serviceCallBack.onError(exceptionRepository.generateSOFUserException(exception))
            } catch (exception: Exception) {
                val baseException = BaseException()
                baseException.code = exception.hashCode()
                baseException.errorMessage = exception.localizedMessage!!
                serviceCallBack.onError(baseException)
            }
        } else {
            serviceCallBack.onSuccess(response.body()!!)
        }
    }
}