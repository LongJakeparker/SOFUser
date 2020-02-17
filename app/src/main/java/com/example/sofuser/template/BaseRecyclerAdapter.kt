package com.example.sofuser.template

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sofuser.R

abstract class BaseRecyclerAdapter(layoutManager: RecyclerView.LayoutManager) :
    RecyclerView.Adapter<BaseViewHolder<Any>>() {
    enum class ItemViewType {
        LOAD_MORE,
        NORMAL
    }

    private var mLoadMoreEnabled: Boolean = false
    protected var mLayoutManager: RecyclerView.LayoutManager? = null
    protected var mColumnNum: Int = 1
    protected var mClickListener: ItemRecyclerClickListener? = null
    private var dataSet: ArrayList<Any> = arrayListOf()


    constructor(layoutManager: RecyclerView.LayoutManager, columnNum: Int) : this(layoutManager) {
        mColumnNum = columnNum
    }

    init {
        mLayoutManager = layoutManager
    }

    fun deleteItemData(position: Int) {
        dataSet.removeAt(position)
        notifyItemRemoved(position)
    }

    fun updateItemData(data: Any, position: Int) {
        dataSet[position] = data
        notifyItemChanged(position)
    }

    fun updateData(dataSet: List<Any>) {
        this.dataSet.clear()
        this.dataSet.addAll(dataSet)
        notifyDataSetChanged()
    }

    fun addData(dataSetAdd: List<Any>) {
        val positionStart = this.dataSet.size
        this.dataSet.addAll(dataSetAdd)
        notifyItemRangeInserted(positionStart, dataSetAdd.size)
    }

    fun setItemClickListener(clickListener: ItemRecyclerClickListener) {
        mClickListener = clickListener
    }

    /**
     * @LocPB function show/hide load more progress bar
     * @param enabled
     */
    fun setLoadMoreEnabled(enabled: Boolean) {
        mLoadMoreEnabled = enabled
        if (!enabled) {
            notifyItemRemoved(itemCount)
        }
        if (mLayoutManager != null && mLayoutManager is GridLayoutManager) {
            if (mLoadMoreEnabled) {
                (mLayoutManager as GridLayoutManager).spanSizeLookup =
                    object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return when (getItemViewType(position)) {
                                ItemViewType.LOAD_MORE.ordinal -> 1
                                ItemViewType.NORMAL.ordinal -> mColumnNum
                                else -> -1
                            }
                        }
                    }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == dataSet.size)
            ItemViewType.LOAD_MORE.ordinal
        else
            ItemViewType.NORMAL.ordinal
    }

    override fun getItemCount(): Int {
        val size = dataSet.size
        val loadMoreItem = if (mLoadMoreEnabled) 1 else 0
        return if (size == 0) {
            0
        } else {
            size + loadMoreItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Any> {
        return when (viewType) {
            ItemViewType.LOAD_MORE.ordinal ->
                LoadMoreFooterViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_loading, parent, false)
                )
            else -> generateViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Any>, position: Int) {
        when (getItemViewType(position)) {
            ItemViewType.NORMAL.ordinal -> updateViewHolder(holder, position)
        }
    }

    abstract fun generateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Any>

    private fun updateViewHolder(holder: BaseViewHolder<Any>, position: Int) {
        holder.bindDataToView(dataSet[position], position)
    }

    class LoadMoreFooterViewHolder(itemView: View) : BaseViewHolder<Any>(itemView) {

        override fun bindDataToView(data: Any, dataPosition: Int) {

        }
    }
}