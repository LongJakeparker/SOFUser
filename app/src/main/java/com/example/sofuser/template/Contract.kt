package com.example.sofuser.template

import android.content.DialogInterface
import androidx.lifecycle.LifecycleOwner

interface Contract {

    interface View {
        /**
         * Show a view with a progress bar indicating a loading process.
         */
        fun showLoading()

        /**
         * Hide a loading view.
         */
        fun hideLoading()

        /**
         * Show an errorMessage message
         *
         * @param message A string representing an errorMessage.
         */
        fun showToast(message: String?)

        /**
         * show an message notify to user
         * @param message
         */
        fun showMessageToUser(message: String?)

        /**
         * show an message notify to user
         * @param message
         */
        fun showMessageToUser(message: String?, clickListener: DialogInterface.OnClickListener?)

        /**
         * check view was destroy or remove out of activity
         */
        fun isDestroy(): Boolean

        fun getLifecycleOwner(): LifecycleOwner
    }

    interface UserActionsListener<V : View> {

        fun bindView(view: V)

        fun unbindView()

        fun onSupportVisible()

        fun onSupportInvisible()
    }
}