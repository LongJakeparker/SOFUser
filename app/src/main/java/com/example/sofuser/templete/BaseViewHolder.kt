package com.example.sofuser.templete

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<Any>(itemView: View):  RecyclerView.ViewHolder(itemView){

    protected val mContext: Context = itemView.context

    abstract fun bindDataToView(data: Any, dataPosition: Int)

}