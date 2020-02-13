package com.example.sofuser.api

interface ServiceCallBack<T> {

    fun onSuccess(result: T)

    fun onError(exception: BaseException)

    fun onLoading(percentDone: Int){}

}