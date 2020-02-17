package com.example.sofuser.repository

import androidx.lifecycle.LiveData
import com.example.sofuser.model.ReputationHistory
import com.example.sofuser.template.Resource

interface ReputationHistoryRepository {
    fun getReputationHistory(page: Int, userId: Int): LiveData<Resource<ArrayList<ReputationHistory>>>
}