package com.example.sofuser.view

import android.view.View
import com.example.sofuser.util.ImageUtil
import com.example.sofuser.model.User
import com.example.sofuser.templete.BaseViewHolder
import kotlinx.android.synthetic.main.item_user_list.view.*

class MainViewHolder constructor(itemView: View, private val callback: MainContract.UserActionsListener?):
    BaseViewHolder<Any>(itemView) {

    override fun bindDataToView(data: Any, dataPosition: Int) {
        data as User

        ImageUtil().loadAvatar(data.userAvatar, itemView.ivUserAvatar)

        itemView.tvUserName.text = data.userName
        itemView.tvReputation.text = data.reputation.toString()
        itemView.tvLocation.text = data.location
    }

}