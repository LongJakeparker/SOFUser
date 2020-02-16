package com.example.sofuser.entity.response

import com.example.sofuser.api.ApiKey
import com.google.gson.annotations.SerializedName

data class ReputationHistoryEntity(
    @SerializedName(ApiKey.POST_ID)
    val postId: Int,

    @SerializedName(ApiKey.REPUTATION_HISTORY_TYPE)
    val reputationHistoryType: String?,

    @SerializedName(ApiKey.REPUTATION_CHANGE)
    val reputationChange: Int?,

    @SerializedName(ApiKey.CREATION_DATE)
    val creationDate: Long?
)