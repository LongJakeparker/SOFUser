package com.example.sofuser.api

import com.example.sofuser.templete.BaseException

interface ServiceCallBack<T> {

    fun onSuccess(result: T)

    fun onError(exception: BaseException)

    fun onLoading(percentDone: Int){}

}