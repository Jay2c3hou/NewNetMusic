package com.example.lib_common_ui

import android.content.Context
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.PixelCopy
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.fragment.app.Fragment

/***
 * 做一个通用的提示视图
 *  1.需要传递视图对应的View 或者 ResID
 *  2.随时随地使用
 *
 *   在窗口上面添加自己的View
 *   Dialog Toast -> Window 最上层
 */
class YYSCAlertView {
    private lateinit var mContext: Context
    private lateinit var mView: View
    private lateinit var mWindowManager: WindowManager
    private lateinit var rootContainer: FrameLayout

    constructor(context: Context, view: View) {
        mContext = context
        mView = view
        mWindowManager = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        rootContainer = FrameLayout(mContext)
    }

    fun show(position: Position = Position.CENTER, autoDismiss: Boolean = true) {
        val lp = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_PANEL,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )
        lp.gravity = when (position) {
            Position.TOP -> Gravity.CENTER_HORIZONTAL or Gravity.TOP
            Position.CENTER -> Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL
            Position.BOTTOM -> Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM
        }
        rootContainer.addView(mView)
        mWindowManager.addView(rootContainer, lp)

        mView.startAnimation(AnimUtils.slideInFromTopAnimation())

        if (autoDismiss) {
            rootContainer.postDelayed({
                //开始动画
                mView.startAnimation(AnimUtils.slideOutToTopAnimation {
                    dismiss()
                })
            }, 200)
        }
    }

    fun dismiss() {
        if (rootContainer.parent != null) {
            mWindowManager.removeView(rootContainer)
        }
    }

    enum class Position {
        CENTER, TOP, BOTTOM
    }
}