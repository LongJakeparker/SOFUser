package com.example.sofuser.util

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogUtil {

    fun showDialogMessage(context: Context, messageId: Int): Dialog {
        return showDialogMessage(context, context.getString(messageId), null, null)
    }

    fun showDialogMessage(
        context: Context,
        title: String,
        message: String?,
        clickListener: DialogInterface.OnClickListener?): Dialog {
        val dialog = MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(context.getString(android.R.string.ok), clickListener)
        dialog.show()
        return dialog.create()
    }

    fun showDialogConfirm(context: Context, titleId: Int, messageId: Int, clickListener: DialogInterface.OnClickListener?): Dialog {
        return showDialogConfirm(context, context.getString(titleId), context.getString(messageId), clickListener)
    }

    fun showDialogConfirm(context: Context, messageId: Int, clickListener: DialogInterface.OnClickListener?): Dialog {
        return showDialogConfirm(context, null, context.getString(messageId), clickListener)
    }

    private fun showDialogConfirm(
        context: Context,
        title: String?,
        message: String,
        clickListener: DialogInterface.OnClickListener?): Dialog {
        val dialog = MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton(android.R.string.cancel, clickListener)
            .setPositiveButton(android.R.string.ok, clickListener)
        dialog.show()
        return dialog.create()
    }

}