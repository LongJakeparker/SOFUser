package com.example.sofuser.view.main

import com.example.sofuser.templete.BaseListContract

interface MainContract {
    interface View : BaseListContract.View {
        fun moveToReputationHistoryDetailScreen(userId: Int?)
    }

    interface UserActionsListener : BaseListContract.UserActionsListener<View> {

    }
}