package com.example.login

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.lib_common_ui.AnimUtils
import org.sufficientlysecure.htmltextview.HtmlTextView


@BindingAdapter("swing", "lgViewModel", requireAll = true)
fun View.shouldSwing(swing: Boolean, lgViewModel: LgLoginViewModel) {
    if (swing) {
        AnimUtils.startSwingAnimation(this)
        lgViewModel.agreementShouldSwing.postValue(false)
    }
}

@BindingAdapter("htmlText")
fun View.htmlText(string: String) {
    val htmlTextView = this as HtmlTextView
    val p1 = string.substring(0, 6)
    val p2 = string.substring(7)
    val colorString = "<font color=#507AAC><b>$p2</b></font>"
    htmlTextView.setHtml("$p1$colorString")
}

/**
 * 给协议选中按钮绑定一个初始值
 */
@BindingAdapter("initSrc")
fun View.initSrc(checked: Boolean) {
    val iv = this as ImageView
    if (checked) {
        iv.setImageResource(R.drawable.ic_checked)
    } else {
        iv.setImageResource(R.drawable.ic_check)
    }
}