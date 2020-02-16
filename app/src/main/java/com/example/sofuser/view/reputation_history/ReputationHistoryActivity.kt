package com.example.sofuser.view.reputation_history

import android.os.Bundle
import com.example.sofuser.Constant
import com.example.sofuser.R
import com.example.sofuser.SOFUserApplication
import com.example.sofuser.templete.BaseListActivity
import com.example.sofuser.templete.BaseRecyclerAdapter


class ReputationHistoryActivity : BaseListActivity(),
    ReputationHistoryContract.View {
    private val presenter = SOFUserApplication.getInjector().provideReputationHistoryPresenter()
    private var userId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.bindView(this)

        if (intent != null) {
            userId = intent.getIntExtra(Constant.EXTRA_USER_ID, 0)
        }
    }

    override fun getAdapter(): BaseRecyclerAdapter {
        val adapter =
            ReputationHistoryAdapter(getLayoutManager())
        adapter.setCallBack(presenter)
        return adapter
    }

    override fun getData(page: Int, isRefresh: Boolean, isLoadMore: Boolean) {
        presenter.loadData(page, isRefresh, isLoadMore, userId)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_reputation_history
    }

    override fun isEnableLoadMore(): Boolean {
        return true
    }

    override fun isShowBackButton(): Boolean {
        return true
    }

    override fun getScreenTitle(): String {
        val title = getString(R.string.title_reputation_history)
        return if (title.isNullOrEmpty()) getString(R.string.app_name) else title
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
    }
}