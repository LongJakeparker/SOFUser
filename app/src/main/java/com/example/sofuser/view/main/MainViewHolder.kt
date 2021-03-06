package com.example.sofuser.view.main

import android.view.View
import com.example.sofuser.R
import com.example.sofuser.util.ImageUtil
import com.example.sofuser.model.User
import com.example.sofuser.template.BaseViewHolder
import com.example.sofuser.util.CommonUtil
import com.example.sofuser.util.DateTimeUtil
import com.example.sofuser.view.bookmark_history.BookmarkHistoryContract
import kotlinx.android.synthetic.main.item_user_list.view.*

class MainViewHolder constructor(itemView: View, private val callback: MainContract.UserActionsListener?):
    BaseViewHolder<Any>(itemView) {

    override fun bindDataToView(data: Any, dataPosition: Int) {
        data as User

        ImageUtil().loadAvatar(data.userAvatar, itemView.ivUserAvatar)

        itemView.tvUserName.text = data.userName
        itemView.tvReputation.text = CommonUtil().formatNumber(data.reputation)
        itemView.tvLocation.text = data.location
        itemView.tvLastAccessDate.text = mContext.getString(R.string.format_last_access, DateTimeUtil().getDate(data.lastAccessDate))

        if (!data.isBookmark) {
            itemView.ivBookmark.setImageResource(R.drawable.ic_bookmark)
        } else {
            itemView.ivBookmark.setImageResource(R.drawable.ic_unbookmark)
        }

        itemView.root.setOnClickListener {
            callback?.clickOnItem(adapterPosition, this)
        }

        itemView.ivBookmark.setOnClickListener {
            if (!data.isBookmark) {
                callback?.clickBookmark(adapterPosition, this)
            } else {
                callback?.clickUnBookmark(adapterPosition, this)
            }
        }
    }

}