package com.example.sofuser.mapper

import com.example.sofuser.entity.response.ReputationHistoryEntity
import com.example.sofuser.entity.response.UserEntity
import com.example.sofuser.model.ReputationHistory
import com.example.sofuser.model.User

class ReputationHistoryEntityMapperImpl : ReputationHistoryEntityMapper {

    override fun transform(reputationHistoryEntity: ReputationHistoryEntity): ReputationHistory {
        return ReputationHistory(
            reputationHistoryEntity.postId,
            reputationHistoryEntity.reputationHistoryType,
            reputationHistoryEntity.reputationChange,
            reputationHistoryEntity.creationDate
        )
    }

    override fun transformCollection(reputationHistoryEntities: ArrayList<ReputationHistoryEntity>?): ArrayList<ReputationHistory>? {
        if (reputationHistoryEntities.isNullOrEmpty())
            return null
        val data = arrayListOf<ReputationHistory>()
        for (entity in reputationHistoryEntities) {
            data.add(transform(entity))
        }
        return data
    }
}