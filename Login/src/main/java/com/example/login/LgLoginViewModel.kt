package com.example.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lib_base.bmob.UserManager

class LgLoginViewModel : ViewModel() {
    private val mUseManager = UserManager.getInstance()
    val phoneNumber = MutableLiveData<String>("")
    var isChecked = MutableLiveData(false)
    val agreementShouldSwing = MutableLiveData(false)
    var time = 59
    fun clearPhoneNumber() {
        phoneNumber.postValue("")
    }

    //请求发送验证码
    fun requestSms(onStart: () -> Unit = {}, onEnd: (Boolean) -> Unit = {}) {
        mUseManager.requestSms(phoneNumber.value!!, onStart = { onStart() }, onEnd = { onEnd(it) })
    }

    //验证码一键注册和登录
    fun verifyAndLogin(code: String, onStart: () -> Unit = {}, onEnd: (Boolean) -> Unit = {}) {
        mUseManager.loginBySmsCode(phoneNumber.value!!, code, onEnd = onEnd)
    }

    //密码登录
    fun loginByPassword(password: String, onStart: () -> Unit = {}, onEnd: (Boolean) -> Unit = {}) {
        mUseManager.loginByPhone(phoneNumber.value!!, password, onStart, onEnd)
    }
}