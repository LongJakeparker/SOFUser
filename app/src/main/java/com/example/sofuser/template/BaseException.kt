package com.example.sofuser.template

import com.google.gson.annotations.SerializedName
import java.lang.Exception

open class BaseException: Exception() {

    @SerializedName("code")
    var code = -1

    @SerializedName("error")
    lateinit var errorMessage: String
}