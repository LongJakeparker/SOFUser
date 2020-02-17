package com.example.sofuser.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sofuser.api.ApiCallback
import com.example.sofuser.api.ApiClient
import com.example.sofuser.api.BaseEntity
import com.example.sofuser.api.ServiceCallBack
import com.example.sofuser.entity.response.ReputationHistoryEntity
import com.example.sofuser.mapper.ReputationHistoryEntityMapper
import com.example.sofuser.model.ReputationHistory
import com.example.sofuser.template.BaseException
import com.example.sofuser.template.Resource

class ReputationHistoryRepositoryImpl constructor(
    private val apiClient: ApiClient,
    private val exceptionRepository: ExceptionRepository,
    private val mapper: ReputationHistoryEntityMapper
) : ReputationHistoryRepository {

    override fun getReputationHistory(
        page: Int,
        userId: Int
    ): LiveData<Resource<ArrayList<ReputationHistory>>> {
        val data = MutableLiveData<Resource<ArrayList<ReputationHistory>>>()
        data.value = Resource.loading(0)
        apiClient.getInterface().getReputationHistory(userId, page).enqueue(
            ApiCallback(exceptionRepository,
                object : ServiceCallBack<BaseEntity<ArrayList<ReputationHistoryEntity>>> {
                    override fun onSuccess(result: BaseEntity<ArrayList<ReputationHistoryEntity>>) {
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