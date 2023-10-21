package com.example.newnetmusic.app

import android.app.Application
import cn.bmob.v3.Bmob

class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Bmob.initialize(applicationContext,"e7c650ef13f75429aceb84bb2d1da617")
    }
}