package com.example.sofuser.view.bookmark_history

import com.example.sofuser.template.BaseListContract
import com.example.sofuser.template.BaseViewHolder

interface BookmarkHistoryContract {
    interface View : BaseListContract.View {
    }

    interface UserActionsListener : BaseListContract.UserActionsListener<View> {
    }
}