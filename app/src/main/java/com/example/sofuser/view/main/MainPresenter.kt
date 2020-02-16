package com.example.sofuser.view.main

import androidx.lifecycle.Observer
import com.example.sofuser.model.User
import com.example.sofuser.repository.UserRepository
import com.example.sofuser.templete.BasePresenter
import com.example.sofuser.templete.BaseViewHolder
import com.example.sofuser.templete.Status

class MainPresenter  constructor(private val userRepository: UserRepository):
    BasePresenter<MainContract.View>(),
    MainContract.UserActionsListener {
    private val listUser = ArrayList<User>()

    override fun loadData(page: Int, isRefresh: Boolean, isLoadMore: Boolean) {
        if (isRefresh)
            listUser.clear()
        getView()?.getLifecycleOwner()?.let {
            userRepository.getListUser(page).observeOnce(
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
                            listUser.addAll(resource.data!!)
                            getView()?.hideLoading()
                            getView()?.onBindData(resource.data)
                        }
                    }
                })
        }
    }

    override fun clickOnItem(position: Int, baseViewHolder: BaseViewHolder<Any>) {
        getView()?.moveToReputationHistoryDetailScreen(listUser[position].userId)
    }

}