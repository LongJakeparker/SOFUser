package com.example.sofuser.repository

import androidx.lifecycle.LiveData
import com.example.sofuser.model.User
import com.example.sofuser.templete.Resource

interface UserRepository {
    fun getListUser(email: String, password: String): LiveData<Resource<User>>
}