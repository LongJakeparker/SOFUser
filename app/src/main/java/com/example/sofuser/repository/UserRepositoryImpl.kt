package com.example.sofuser.repository

import androidx.lifecycle.LiveData
import com.example.sofuser.model.User
import com.example.sofuser.templete.Resource

class UserRepositoryImpl constructor(
    private val apiClient: ApiClient,
    private val exceptionRepository: ExceptionRepository,
    private val mapper: RestaurantProfileEntityMapper
) : UserRepository {

    override fun getListUser(email: String, password: String): LiveData<Resource<User>> {

    }

}