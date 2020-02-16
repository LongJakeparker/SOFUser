package com.example.sofuser.mapper

import com.example.sofuser.entity.response.ReputationHistoryEntity
import com.example.sofuser.entity.response.UserEntity
import com.example.sofuser.model.ReputationHistory
import com.example.sofuser.model.User

interface ReputationHistoryEntityMapper {

    fun transform(reputationHistoryEntity: ReputationHistoryEntity): ReputationHistory

    fun transformCollection(reputationHistoryEntities: ArrayList<ReputationHistoryEntity>?): ArrayList<ReputationHistory>?
}