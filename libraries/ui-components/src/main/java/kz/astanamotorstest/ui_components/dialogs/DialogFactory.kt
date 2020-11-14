package kz.astanamotorstest.ui_components.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import kz.astanamotorstest.ui_components.R

object DialogFactory {
    fun getProgressDialog(context: Context): Dialog =
        AlertDialog.Builder(context).setCancelable(false).setView(R.layout.dialog_progress).create()
}