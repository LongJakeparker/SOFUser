package com.example.sofuser.view

import com.example.sofuser.templete.Contract

interface MainContract {
    interface View: Contract.View {
        fun moveToReputationHistoryDetailScreen(orderId: String?)
    }

    interface UserActionsListener: Contract.UserActionsListener<View> {
        fun loadData()
    }
}