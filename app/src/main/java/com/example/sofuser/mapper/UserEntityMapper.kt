package com.example.sofuser.mapper

import com.example.sofuser.entity.response.UserEntity
import com.example.sofuser.model.User

interface UserEntityMapper {

    fun transform(userEntity: UserEntity): User

    fun transformCollection(userEntities: ArrayList<UserEntity>?): ArrayList<User>?
}