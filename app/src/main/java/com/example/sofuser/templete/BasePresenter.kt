package com.example.sofuser.templete

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

abstract class BasePresenter<V : Contract.View>:
    Contract.UserActionsListener<V> {

    private var mAttachedView: V? = null

    override fun bindView(view: V) {
        mAttachedView = view
    }

    override fun unbindView() {
        mAttachedView = null
    }

    override fun onSupportVisible() {
    }

    override fun onSupportInvisible() {
    }

    fun getView(): V? {
        if (mAttachedView == null || mAttachedView!!.isDestroy())
            return null
        return mAttachedView
    }

    protected fun checkViewExist(): Boolean {
        return getView() != null && getView()!!.isDestroy()
    }

    fun <T> LiveData<Resource<T>>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<Resource<T>>) {
        observe(lifecycleOwner, object : Observer<Resource<T>> {
            override fun onChanged(t: Resource<T>?) {
                observer.onChanged(t)
                if (t?.status != Status.LOADING) {
                    removeObserver(this)
                }
            }
        })
    }
}