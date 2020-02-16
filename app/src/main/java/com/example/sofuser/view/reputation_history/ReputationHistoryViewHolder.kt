package com.example.sofuser.view.reputation_history

import android.view.View
import com.example.sofuser.model.ReputationHistory
import com.example.sofuser.templete.BaseViewHolder
import com.example.sofuser.util.DateTimeUtil
import kotlinx.android.synthetic.main.item_reputation_history.view.*

class ReputationHistoryViewHolder constructor(itemView: View, private val callback: ReputationHistoryContract.UserActionsListener?):
    BaseViewHolder<Any>(itemView) {

    override fun bindDataToView(data: Any, dataPosition: Int) {
        data as ReputationHistory

        itemView.tvPostId.text = data.postId.toString()
        itemView.tvReputation.text = data.reputationChange.toString()
        itemView.tvReputationType.text = data.reputationHistoryType
        itemView.tvCreationDate.text = DateTimeUtil().getDate(data.creationDate)
    }

}