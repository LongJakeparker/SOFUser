package com.example.sofuser.view

import com.example.sofuser.repository.UserRepository
import com.example.sofuser.templete.BasePresenter

class MainPresenter  constructor(private val userRepository: UserRepository):
    BasePresenter<MainContract.View>(),
    MainContract.UserActionsListener {

    override fun loadData() {

    }

}