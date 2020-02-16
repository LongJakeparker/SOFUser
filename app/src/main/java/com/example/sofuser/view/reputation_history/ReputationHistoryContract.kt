package com.example.sofuser.view.reputation_history

import com.example.sofuser.templete.BaseListContract

interface ReputationHistoryContract {
    interface View : BaseListContract.View {

    }

    interface UserActionsListener : BaseListContract.UserActionsListener<View> {
        fun loadData(page: Int, isRefresh: Boolean, isLoadMore: Boolean, userId: Int?)
    }
}