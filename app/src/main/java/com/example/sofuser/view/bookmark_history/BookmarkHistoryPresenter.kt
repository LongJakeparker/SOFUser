package com.example.sofuser.view.bookmark_history

import com.example.sofuser.repository.UserRepository
import com.example.sofuser.template.BasePresenter
import com.example.sofuser.template.BaseViewHolder

class BookmarkHistoryPresenter constructor(private val userRepository: UserRepository) :
    BasePresenter<BookmarkHistoryContract.View>(),
    BookmarkHistoryContract.UserActionsListener {

    override fun loadData(page: Int, isRefresh: Boolean, isLoadMore: Boolean) {

    }

    override fun clickOnItem(position: Int, baseViewHolder: BaseViewHolder<Any>) {

    }

}