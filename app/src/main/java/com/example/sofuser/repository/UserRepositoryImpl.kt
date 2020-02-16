package com.example.sofuser.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sofuser.api.ApiCallback
import com.example.sofuser.api.ApiClient
import com.example.sofuser.api.BaseEntity
import com.example.sofuser.api.ServiceCallBack
import com.example.sofuser.entity.response.UserEntity
import com.example.sofuser.mapper.UserEntityMapper
import com.example.sofuser.model.User
import com.example.sofuser.templete.BaseException
import com.example.sofuser.templete.Resource

class UserRepositoryImpl constructor(
    private val apiClient: ApiClient,
    private val exceptionRepository: ExceptionRepository,
    private val mapper: UserEntityMapper
) : UserRepository {

    override fun getListUser(page: Int): LiveData<Resource<ArrayList<User>>> {
        val data = MutableLiveData<Resource<ArrayList<User>>>()
        data.value = Resource.loading(0)
        apiClient.getInterface().getListUser(page).enqueue(
            ApiCallback(exceptionRepository,
                object : ServiceCallBack<BaseEntity<ArrayList<UserEntity>>> {
                    override fun onSuccess(result: BaseEntity<ArrayList<UserEntity>>) {
                        val model = mapper.transformCollection(result.getResult()!!)
                        data.value = Resource.success(model)
                    }

                    override fun onError(exception: BaseException) {
                        data.value = Resource.error(exception)
                    }

                })
        )
        return data
    }

}