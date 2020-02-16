package com.example.sofuser.api

import com.example.sofuser.entity.response.ReputationHistoryEntity
import com.example.sofuser.entity.response.UserEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {

    @GET("users")
    fun getListUser(
        @Query(ApiKey.PAGE) page: Int,
        @Query(ApiKey.PAGESIZE) pageSize: Int = 30,
        @Query(ApiKey.SITE) site: String = "stackoverflow"
    ): Call<BaseEntity<ArrayList<UserEntity>>>

    @GET("users/{userId}/reputation-history")
    fun getReputationHistory(
        @Path("userId") userId: Int,
        @Query(ApiKey.PAGE) page: Int,
        @Query(ApiKey.PAGESIZE) pageSize: Int = 30,
        @Query(ApiKey.SITE) site: String = "stackoverflow"
    ): Call<BaseEntity<ArrayList<ReputationHistoryEntity>>>
}