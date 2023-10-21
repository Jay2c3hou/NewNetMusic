package com.example.lib_base.bmob

import cn.bmob.v3.BmobUser

class UserManager {
    private var mUser: User? = null
    private var mBmob = BmobManager.getInstance()

    companion object {
        private var instance: UserManager? = null
        fun getInstance(): UserManager {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) instance = UserManager()
                }
            }
            return instance!!
        }
    }

    //是否登录
    fun isLogin() = BmobManager.getInstance().isLogin()

    //请求验证码
    fun requestSms(phone: String, onStart: () -> Unit = {}, onEnd: (Boolean) -> Unit = {}) {
        onStart()
        mBmob.requestSms(phone) {
            onEnd(it)
        }
    }

    /**
     * 短信验证码登录
     */
    fun loginBySmsCode(
        phone: String,
        code: String,
        onStart: () -> Unit = {},
        onEnd: (Boolean) -> Unit = {}
    ) {
        onStart()
        mBmob.signOrLoginByMobilePhone(phone, code) {
            if (it) { //成功就获取当前登录的用户信息
                mUser = mBmob.currentUser()
            }
            onEnd(it)
        }
    }

    fun loginByPhone(
        phone: String,
        code: String,
        onStart: () -> Unit = {},
        onEnd: (Boolean) -> Unit = {}
    ) {
        onStart()
        mBmob.loginByAccount(phone, code) {
            if (it)  mUser = mBmob.currentUser()
            onEnd(it)
        }
    }

    /**
     * 退出登录
     */
    fun logout() {
        mBmob.logout()
        mUser = null
    }
}