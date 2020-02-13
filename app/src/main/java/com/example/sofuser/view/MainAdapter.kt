package com.example.sofuser.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sofuser.R
import com.example.sofuser.templete.BaseRecyclerAdapter
import com.example.sofuser.templete.BaseViewHolder

class MainAdapter constructor(layoutManager: RecyclerView.LayoutManager): BaseRecyclerAdapter(layoutManager) {

    private var mCallBack: MainContract.UserActionsListener? = null

    fun setCallBack(callback: MainContract.UserActionsListener) {
        mCallBack = callback
    }

    override fun generateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Any> {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user_list, parent,
                false), mCallBack)
    }
}