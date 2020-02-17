package com.example.sofuser.template

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.LifecycleOwner
import com.example.sofuser.R
import com.example.sofuser.util.DialogUtil
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout


abstract class BaseActivity : AppCompatActivity(), Contract.View, Toolbar.OnMenuItemClickListener {
    protected var mToolBar: Toolbar? = null
    protected var mCollapsingToolbarLayout: CollapsingToolbarLayout? = null
    protected var appBarLayout: AppBarLayout? = null
    private val mLoadingDialog = LoadingDialogFragment()

    protected abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        mToolBar = findViewById(R.id.appBar)
        mCollapsingToolbarLayout = findViewById(R.id.collapsingToolBar)
        appBarLayout = findViewById(R.id.appBarLayout)
        initToolbarNav()
    }

    protected open fun getTitleBackgroundColor(): Int {
        return Color.BLACK
    }

    protected open fun getScreenTitleId(): Int {
        return 0
    }

    protected open fun getOptionMenu(): Int {
        return 0
    }

    protected open fun getScreenTitle(): String {
        return ""
    }

    protected open fun isShowBackButton(): Boolean {
        return false
    }

    protected open fun initToolbarNav() {
        mCollapsingToolbarLayout?.setCollapsedTitleTextAppearance(R.style.TextAppearance_Shikomel_Title_Collapse)
        mCollapsingToolbarLayout?.setExpandedTitleTextAppearance(R.style.TextAppearance_Shikomel_Title_Expand)

        if (appBarLayout?.layoutParams != null) {
            val params = appBarLayout?.layoutParams as CoordinatorLayout.LayoutParams
            val behavior = AppBarLayout.Behavior()
            behavior.setDragCallback(object : AppBarLayout.Behavior.DragCallback() {
                override fun canDrag(appBarLayout: AppBarLayout): Boolean {
                    return false
                }
            })
            params.behavior = behavior
        }

        if (isShowBackButton()) {
            mToolBar?.setNavigationIcon(R.drawable.ic_back)
            mToolBar?.setNavigationOnClickListener {
                hideSoftInput()
                onBackPressed()
            }
        }

        if (getScreenTitle().isNotEmpty()) {
            mCollapsingToolbarLayout?.title = getScreenTitle()
        } else if (getScreenTitleId() > 0) {
            mCollapsingToolbarLayout?.title = getString(getScreenTitleId())
        }

        showOptionMenu(mToolBar)
    }

    protected fun changeToolbarTitle(title: String) {
        mCollapsingToolbarLayout?.title = title
    }

    private fun showOptionMenu(toolbar: Toolbar?) {
        if (getOptionMenu() != 0) {
            toolbar?.menu?.clear()
            toolbar?.inflateMenu(getOptionMenu())
            toolbar?.setOnMenuItemClickListener(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menu is MenuBuilder) {
            menu.setOptionalIconsVisible(true)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return false
    }

    protected fun hideSoftInput() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun showProgressDialog() {
        if (mLoadingDialog.isVisible.not() && mLoadingDialog.isAdded.not()) {
            mLoadingDialog.show(supportFragmentManager, mLoadingDialog.javaClass.name)
        }
    }

    private fun dismissProgressDialog() {
        if (mLoadingDialog.isVisible || mLoadingDialog.isAdded)
            mLoadingDialog.dismissAllowingStateLoss()
    }

    override fun showToast(message: String?) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    fun showMessage(message: String?) {
        showMessage(message, null)
    }

    fun showMessage(message: String?, listener: DialogInterface.OnClickListener?) {
        DialogUtil().showDialogMessage(this, "", message, listener)
    }

    override fun showLoading() {
        showProgressDialog()
    }

    override fun hideLoading() {
        dismissProgressDialog()
    }

    override fun showMessageToUser(message: String?) {
        showMessage(message)
    }

    override fun showMessageToUser(
        message: String?,
        clickListener: DialogInterface.OnClickListener?
    ) {
        showMessage(message, clickListener)
    }

    override fun isDestroy(): Boolean {
        return isDestroyed
    }

    override fun getLifecycleOwner(): LifecycleOwner {
        return this
    }

}