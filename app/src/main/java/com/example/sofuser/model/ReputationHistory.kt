package com.example.sofuser.model

data class ReputationHistory(
    val postId: Int,
    val reputationHistoryType: String? = null,
    val reputationChange: Int? = null,
    val creationDate: Long? = null
)