package com.example.sofuser.entity.response

import com.example.sofuser.api.ApiKey
import com.google.gson.annotations.SerializedName

data class UserEntity(
    @SerializedName(ApiKey.USER_ID)
    val userId: Int,

    @SerializedName(ApiKey.DISPLAY_NAME)
    val userName: String?,

    @SerializedName(ApiKey.PROFILE_IMAGE)
    val userAvatar: String?,

    @SerializedName(ApiKey.REPUTATION)
    val reputation: Int?,

    @SerializedName(ApiKey.LOCATION)
    val location: String?,

    @SerializedName(ApiKey.LAST_ACCESS_DATE)
    val lastAccessDate: Long?
)