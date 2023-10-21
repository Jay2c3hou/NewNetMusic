package com.example.login.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.TextView
import com.example.lib_common_ui.YYSCAlertView
import com.example.login.R
import java.lang.ref.WeakReference

var yyscAlertView: WeakReference<YYSCAlertView>? = null

object ViewUtils {

    /**
     * 弹出顶部的提示视图 YYSCAlertView
     */
    @SuppressLint("InflateParams")
    fun showAlert(context: Context, text: String?, isNormal: Boolean) {
        val view = if (isNormal) {
            LayoutInflater.from(context).inflate(R.layout.layout_login_normal_alert, null)
        } else {
            LayoutInflater.from(context).inflate(R.layout.layout_login_alert, null)
        }
        if (text != null) {
            view.findViewById<TextView>(R.id.alertTextView).text = text
        }

        YYSCAlertView(context, view).show(YYSCAlertView.Position.TOP)
    }

    fun showIsLoading(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.login_loading_layout, null)
        var progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val alertView = YYSCAlertView(context, view)
        alertView.show(YYSCAlertView.Position.CENTER, false)
        yyscAlertView = WeakReference(alertView)
    }

    fun dismissLoading() {
        yyscAlertView?.let {
            it.get()?.dismiss()
        }
    }

}