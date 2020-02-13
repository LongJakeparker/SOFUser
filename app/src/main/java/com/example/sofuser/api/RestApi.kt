package com.example.sofuser.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RestApi {

    @FormUrlEncoded
    @POST("functions/m_userLogin")
    fun login(@Field(ApiKey.USER_NAME) email: String?, @Field(ApiKey.PASSWORD) passWord: String?): Call<BaseEntity<RestaurantEntity>>
}