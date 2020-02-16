package com.example.sofuser.templete

import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sofuser.R
import kotlinx.android.synthetic.main.activity_base_list.*

abstract class BaseListActivity: BaseActivity(), BaseListContract.View, ItemRecyclerClickListener {

    protected var mPageLimitNumber = 30
    private lateinit var mLoadMoreListener: LoadMoreOnScrollListener
    protected lateinit var mAdapter: BaseRecyclerAdapter
    private var mLoadMoreIndex = 0
    private var mLoadMoreEnable = false
    private lateinit var mLayoutManager : RecyclerView.LayoutManager

    protected abstract fun getAdapter(): BaseRecyclerAdapter
    protected abstract fun getData(page: Int, isRefresh: Boolean, isLoadMore: Boolean)

    protected open fun isEnableLoadMore(): Boolean {
        return true
    }

    protected open fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_base_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mLayoutManager = getLayoutManager()
        mLoadMoreListener = object : LoadMoreOnScrollListener(mLayoutManager) {
            override fun onLoadMore() {
                if (mLoadMoreEnable)
                    loadMore()
            }
        }
        rvContent?.setHasFixedSize(true)
        rvContent?.layoutManager = mLayoutManager
        rvContent.addOnScrollListener(mLoadMoreListener)
        srlContent?.setOnRefreshListener {
            reload()
        }
        mAdapter = getAdapter()
        setLoadMoreStatus(isEnableLoadMore())
        mAdapter.setItemClickListener(this)
        rvContent?.adapter = mAdapter

        mLoadMoreIndex = 1
        Handler().postDelayed({
            getData(mLoadMoreIndex, isRefresh = false, isLoadMore = false)
        }, 200)
    }

    private fun loadMore() {
        mLoadMoreIndex++
        getData(mLoadMoreIndex, isRefresh = false, isLoadMore = true)
    }

    private fun reload() {
        setLoadMoreStatus(isEnableLoadMore())
        mLoadMoreIndex = 1
        getData(mLoadMoreIndex, isRefresh = true, isLoadMore = false)
    }

    override fun notifyUpdateItem(data: Any, position: Int) {
        mAdapter.updateItemData(data, position)
    }

    override fun notifyDeleteItem(position: Int) {
        mAdapter.deleteItemData(position)
    }

    override fun onBindData(data: List<Any>) {
        bindDataToView(data)
    }

    override fun onItemClick(position: Int, baseViewHolder: BaseViewHolder<Any>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setLoadMoreStatus(enableLoadMore: Boolean) {
        mLoadMoreEnable = enableLoadMore
        if (enableLoadMore) {
            enableLoadMore()
        } else {
            disableLoadMore()
        }
    }

    private fun disableLoadMore() {
        rvContent.removeOnScrollListener(mLoadMoreListener)
        mAdapter.setLoadMoreEnabled(false)
        mAdapter.notifyItemRemoved(mAdapter.itemCount)
    }

    private fun enableLoadMore() {
        rvContent.removeOnScrollListener(mLoadMoreListener)
        rvContent.addOnScrollListener(mLoadMoreListener)
        mLoadMoreListener.setLoadingFinished()
        mAdapter.setLoadMoreEnabled(true)
    }

    private fun bindDataToView(data: List<Any>) {
        setRefreshing(false)
        mLoadMoreListener.setLoadingFinished()
        if (mLoadMoreIndex > 1) {
            mAdapter.addData(data)
        } else {
            if (data.isEmpty()) {
            } else {
                mAdapter.updateData(data)
            }
        }
        if (data.size < mPageLimitNumber) {
            setLoadMoreStatus(false)
        }
    }

    private fun setRefreshing(isRefreshing: Boolean) {
        srlContent?.isRefreshing = isRefreshing
    }

    override fun hideLoading() {
        super.hideLoading()
        setRefreshing(false)
    }

}