package com.example.sofuser.view.main

import android.content.Intent
import android.os.Bundle
import com.example.sofuser.Constant
import com.example.sofuser.R
import com.example.sofuser.SOFUserApplication
import com.example.sofuser.templete.BaseListActivity
import com.example.sofuser.templete.BaseRecyclerAdapter
import com.example.sofuser.view.reputation_history.ReputationHistoryActivity

class MainActivity : BaseListActivity(),
    MainContract.View {
    private val presenter = SOFUserApplication.getInjector().provideMainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.bindView(this)
    }

    override fun getAdapter(): BaseRecyclerAdapter {
        val adapter =
            MainAdapter(getLayoutManager())
        adapter.setCallBack(presenter)
        return adapter
    }

    override fun getData(page: Int, isRefresh: Boolean, isLoadMore: Boolean) {
        presenter.loadData(page, isRefresh, isLoadMore)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun isEnableLoadMore(): Boolean {
        return true
    }

    override fun getScreenTitle(): String {
        val title = getString(R.string.title_user_list)
        return if (title.isNullOrEmpty()) getString(R.string.app_name) else title
    }

    override fun moveToReputationHistoryDetailScreen(userId: Int?) {
        val intent = Intent(this, ReputationHistoryActivity::class.java)
        intent.putExtra(Constant.EXTRA_USER_ID, userId)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
    }
}
