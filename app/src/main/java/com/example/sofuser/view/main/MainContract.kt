package com.example.sofuser.view.main

import com.example.sofuser.template.BaseListContract
import com.example.sofuser.template.BaseViewHolder

interface MainContract {
    interface View : BaseListContract.View {
        fun moveToReputationHistoryDetailScreen(userId: Int?)

        fun moveToBookmarkHistoryScreen()
    }

    interface UserActionsListener : BaseListContract.UserActionsListener<View> {
        fun clickBookmark(position: Int, baseViewHolder: BaseViewHolder<Any>)

        fun clickUnBookmark(position: Int, baseViewHolder: BaseViewHolder<Any>)
    }
}