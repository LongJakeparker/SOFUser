package com.example.sofuser.repository

import androidx.lifecycle.LiveData
import com.example.sofuser.model.User
import com.example.sofuser.templete.Resource

interface UserRepository {
    fun getListUser(page: Int): LiveData<Resource<ArrayList<User>>>
}