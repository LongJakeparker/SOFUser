package com.example.sofuser.api

import com.google.gson.annotations.SerializedName

open class BaseEntity<T> {

    // code when error
    @SerializedName("code")
    private var code: Int? = null

    @SerializedName("error")
    private var message: String? = null

    @SerializedName("result")
    private var result: T? = null

    fun getCode(): Int? {
        return code
    }

    fun getMessage(): String? {
        return message
    }

    /**
     * function check error or not
     */
    fun isError(): Boolean {
        return code != null
    }

    fun getResult(): T? {
        return result
    }

}