package com.example.sofuser.view.bookmark_history

import android.os.Bundle
import com.example.sofuser.R
import com.example.sofuser.SOFUserApplication
import com.example.sofuser.template.BaseListActivity
import com.example.sofuser.template.BaseRecyclerAdapter
import com.example.sofuser.util.SharedPreferenceManager

class BookmarkHistoryActivity : BaseListActivity(),
    BookmarkHistoryContract.View {

    private val presenter = SOFUserApplication.getInjector().provideBookmarkHistoryPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.bindView(this)
    }

    override fun getAdapter(): BaseRecyclerAdapter {
        val adapter =
            BookmarkHistoryAdapter(getLayoutManager())
        adapter.setCallBack(presenter)
        return adapter
    }

    override fun getData(page: Int, isRefresh: Boolean, isLoadMore: Boolean) {
        onBindData(SharedPreferenceManager.getListBookmarkUser())
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun isEnableLoadMore(): Boolean {
        return true
    }

    override fun isShowBackButton(): Boolean {
        return true
    }

    override fun getScreenTitle(): String {
        val title = getString(R.string.title_bookmark_users)
        return if (title.isNullOrEmpty()) getString(R.string.app_name) else title
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
    }
}
