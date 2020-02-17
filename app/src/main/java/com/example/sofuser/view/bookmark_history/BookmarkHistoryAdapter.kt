package com.example.sofuser.view.bookmark_history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sofuser.R
import com.example.sofuser.template.BaseRecyclerAdapter
import com.example.sofuser.template.BaseViewHolder
import com.example.sofuser.view.main.MainContract

class BookmarkHistoryAdapter constructor(layoutManager: RecyclerView.LayoutManager): BaseRecyclerAdapter(layoutManager) {

    private var mCallBack: BookmarkHistoryContract.UserActionsListener? = null

    fun setCallBack(callback: BookmarkHistoryContract.UserActionsListener) {
        mCallBack = callback
    }

    override fun generateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Any> {
        return BookmarkHistoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user_list, parent,
                false
            ), mCallBack
        )
    }
}