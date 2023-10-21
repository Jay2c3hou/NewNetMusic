package com.example.login

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.lib_common_ui.AnimUtils
import com.example.login.LgLoginViewModel
import com.example.login.utils.ViewUtils

class LgClickEvent {
    /**
     * 输入框右边删除按钮点击
     * 清空手机号
     */
    @BindingAdapter("clearPhone")
    fun clearPhone(view: View, viewModel: LgLoginViewModel) {
        viewModel.clearPhoneNumber()
    }

    @BindingAdapter("login")
    fun login(view: View, viewModel: LgLoginViewModel) {
        val phone = viewModel.phoneNumber.value
        if (phone == null || phone.length != 11) {
            AnimUtils.startSwingAnimation(view)
            ViewUtils.showAlert(view.context, "手机号应为11位数字", true)
            return
        }
        //用户是否同意协议
        if (!viewModel.isChecked.value!!) {
            viewModel.agreementShouldSwing.postValue(true)
            ViewUtils.showAlert(view.context,"同意协议后继续",true)
            return
        }
    }

    /**
     * 同意协议的按钮点击事件
     */
    @BindingAdapter("changeSelectState")
    fun changeSelectState(view: View, viewModel: LgLoginViewModel) {
        viewModel.isChecked.postValue(!viewModel.isChecked.value!!)
    }

}