package com.example.sofuser.model

data class User(
    val userId: Int,
    val userName: String? = null,
    val userAvatar: String? = null,
    val reputation: Int? = null,
    val location: String? = null,
    val lastAccessDate: Long? = null
)