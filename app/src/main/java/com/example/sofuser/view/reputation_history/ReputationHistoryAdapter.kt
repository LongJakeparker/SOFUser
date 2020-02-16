package com.example.sofuser.view.reputation_history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sofuser.R
import com.example.sofuser.templete.BaseRecyclerAdapter
import com.example.sofuser.templete.BaseViewHolder

class ReputationHistoryAdapter constructor(layoutManager: RecyclerView.LayoutManager): BaseRecyclerAdapter(layoutManager) {

    private var mCallBack: ReputationHistoryContract.UserActionsListener? = null

    fun setCallBack(callback: ReputationHistoryContract.UserActionsListener) {
        mCallBack = callback
    }

    override fun generateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Any> {
        return ReputationHistoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_reputation_history, parent,
                false
            ), mCallBack
        )
    }
}