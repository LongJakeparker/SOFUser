package com.example.sofuser.view.reputation_history

import androidx.lifecycle.Observer
import com.example.sofuser.model.ReputationHistory
import com.example.sofuser.repository.ReputationHistoryRepository
import com.example.sofuser.templete.BasePresenter
import com.example.sofuser.templete.BaseViewHolder
import com.example.sofuser.templete.Status

class ReputationHistoryPresenter constructor(private val reputationHistoryRepository: ReputationHistoryRepository):
    BasePresenter<ReputationHistoryContract.View>(),
    ReputationHistoryContract.UserActionsListener {
    private val listReputationHistory = ArrayList<ReputationHistory>()

    override fun loadData(page: Int, isRefresh: Boolean, isLoadMore: Boolean, userId: Int?) {
        if (userId == null)
            return

        if (isRefresh)
            listReputationHistory.clear()
        getView()?.getLifecycleOwner()?.let {
            reputationHistoryRepository.getReputationHistory(page, userId).observeOnce(
                it, Observer { resource ->
                    when (resource.status) {
                        Status.LOADING -> {
                            if (isLoadMore.not() && isRefresh.not())
                                getView()?.showLoading()
                        }
                        Status.ERROR -> {
                            getView()?.showToast(resource.exception?.errorMessage)
                        }
                        Status.SUCCESS -> {
                            listReputationHistory.addAll(resource.data!!)
                            getView()?.hideLoading()
                            getView()?.onBindData(resource.data)
                        }
                    }
                })
        }
    }

    override fun loadData(page: Int, isRefresh: Boolean, isLoadMore: Boolean) {

    }

    override fun clickOnItem(position: Int, baseViewHolder: BaseViewHolder<Any>) {

    }

}