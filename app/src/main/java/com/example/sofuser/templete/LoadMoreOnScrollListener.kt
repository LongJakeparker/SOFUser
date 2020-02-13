package com.example.sofuser.templete

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * @author Phan Bao Loc
 * @since 12/18/2019
 */
abstract class LoadMoreOnScrollListener constructor(private val layoutManager: RecyclerView.LayoutManager):
    RecyclerView.OnScrollListener() {

    private val visibleThreshold = 1
    private var loading = false
    private var gridLayoutManager: GridLayoutManager? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var staggeredGridLayoutManager: StaggeredGridLayoutManager? = null

    init {
        when (layoutManager) {
            is GridLayoutManager -> {
                gridLayoutManager = layoutManager
                linearLayoutManager = null
                staggeredGridLayoutManager = null
            }
            is LinearLayoutManager -> {
                gridLayoutManager = null
                staggeredGridLayoutManager = null
                linearLayoutManager = layoutManager
            }
            else -> {
                gridLayoutManager = null
                linearLayoutManager = null
                staggeredGridLayoutManager = layoutManager as StaggeredGridLayoutManager?
            }
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (!loading && layoutManager.itemCount > 0 && findFirstVisibleItemPosition() + recyclerView.childCount + visibleThreshold >= layoutManager!!.itemCount
        ) {
            onLoadMore()
            loading = true
        }
    }

    fun setLoadingFinished() {
        loading = false
    }

    fun isLoading(): Boolean {
        return loading
    }

    abstract fun onLoadMore()

    private fun findFirstVisibleItemPosition(): Int {
        if (gridLayoutManager != null) return gridLayoutManager!!.findFirstVisibleItemPosition()
        if (linearLayoutManager != null) return linearLayoutManager!!.findFirstVisibleItemPosition()
        return if (staggeredGridLayoutManager != null) staggeredGridLayoutManager!!.findFirstVisibleItemPositions(
            null
        )[0] else 0
    }
}