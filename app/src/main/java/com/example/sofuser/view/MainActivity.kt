package com.example.sofuser.view

import androidx.lifecycle.LifecycleOwner
import com.example.sofuser.R
import com.example.sofuser.SOFUserApplication
import com.example.sofuser.templete.BaseListActivity
import com.example.sofuser.templete.BaseRecyclerAdapter

class MainActivity : BaseListActivity() {
    private val presenter = SOFUserApplication.getInjector().provideMainPresenter()

    override fun getAdapter(): BaseRecyclerAdapter {
        val adapter = MainAdapter(getLayoutManager())
        adapter.setCallBack(presenter)
        return adapter
    }

    override fun getData(page: Int, isRefresh: Boolean, isLoadMore: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isDestroy(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLifecycleOwner(): LifecycleOwner {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }
}
