package com.example.sofuser.view.main

import androidx.lifecycle.Observer
import com.example.sofuser.model.User
import com.example.sofuser.repository.UserRepository
import com.example.sofuser.template.BasePresenter
import com.example.sofuser.template.BaseViewHolder
import com.example.sofuser.template.Status
import com.example.sofuser.util.SharedPreferenceManager

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
                            for (user in resource.data!!) {
                                if (SharedPreferenceManager.checkExist(user.userId)) {
                                    user.isBookmark = true
                                }
                            }
                            listUser.addAll(resource.data)
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

    override fun clickBookmark(position: Int, baseViewHolder: BaseViewHolder<Any>) {
        listUser[position].isBookmark = true
        SharedPreferenceManager.setBookmarkUser(listUser[position])
        getView()?.notifyUpdateItem(listUser[position], position)
    }

    override fun clickUnBookmark(position: Int, baseViewHolder: BaseViewHolder<Any>) {
        listUser[position].isBookmark = false
        SharedPreferenceManager.removeBookmarkUser(listUser[position].userId)
        getView()?.notifyUpdateItem(listUser[position], position)
    }

}