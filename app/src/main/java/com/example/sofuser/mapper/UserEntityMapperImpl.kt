package com.example.sofuser.mapper

import com.example.sofuser.entity.response.UserEntity
import com.example.sofuser.model.User

class UserEntityMapperImpl : UserEntityMapper {

    override fun transform(userEntity: UserEntity): User {
        return User(
            userEntity.userId,
            userEntity.userName,
            userEntity.userAvatar,
            userEntity.reputation,
            userEntity.location,
            userEntity.lastAccessDate
        )
    }

    override fun transformCollection(userEntities: ArrayList<UserEntity>?): ArrayList<User>? {
        if (userEntities.isNullOrEmpty())
            return null
        val data = arrayListOf<User>()
        for (entity in userEntities) {
            data.add(transform(entity))
        }
        return data
    }
}