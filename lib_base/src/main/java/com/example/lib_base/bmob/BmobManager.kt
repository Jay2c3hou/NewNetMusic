package com.example.lib_base.bmob

import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobSMS
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.LogInListener
import cn.bmob.v3.listener.QueryListener

class BmobManager private constructor() {
    companion object {
        private var instance: BmobManager? = null
        fun getInstance(): BmobManager {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) instance = BmobManager()
                }
            }
            return instance!!
        }
    }

    //是否登录
    fun isLogin() = BmobUser.isLogin()

    //获取当前登入的用户
    fun currentUser() = BmobUser.getCurrentUser(User::class.java)

    fun requestSms(phone: String, callback: (Boolean) -> Unit = {}) {
        BmobSMS.requestSMSCode(phone, "", object : QueryListener<Int>() {
            override fun done(p0: Int?, p1: BmobException?) {
                callback(p1 == null)
            }
        })
    }

    /**
     * 一键登录和注册
     */

    fun signOrLoginByMobilePhone(phone: String, code: String, callback: (Boolean) -> Unit = {}) {
        BmobUser.signOrLoginByMobilePhone(phone, code, object : LogInListener<BmobUser>() {
            override fun done(p0: BmobUser?, p1: BmobException?) {
                callback(p1 == null)
            }
        })
    }

    /**
     * 手机和密码登录
     */
    fun loginByAccount(phone: String, password: String, callback: (Boolean) -> Unit = {}) {
        BmobUser.loginByAccount(phone, password, object : LogInListener<User>() {
            override fun done(p0: User?, p1: BmobException?) {
                callback(p1 == null)
            }
        })
    }

    /**
     * 查询Adv表里面的所有信息
     */
//    fun queryAdv(OnStart: () -> Unit = {}, onEnd: (List<Adv>) -> Unit = {}) {
//
//    }

    fun logout() = BmobUser.logOut()
}