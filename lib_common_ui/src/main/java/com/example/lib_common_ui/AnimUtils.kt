package com.example.lib_common_ui

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.BounceInterpolator
import android.view.animation.TranslateAnimation

object AnimUtils {
    @SuppressLint("RestrictedApi")
    fun startSwingAnimation(target: View, scope: Int = 20) {
        val pxScope = dp2px(target.context, scope.toFloat())

        ObjectAnimator.ofFloat(
            target,
            "translationX",
            0f,
            -pxScope,
            0f,
            pxScope,
            0f
        ).apply {
            duration = 222
            repeatCount = 3
            repeatMode = ObjectAnimator.RESTART
            start()
        }
    }

    //从头部进入动画
    fun slideInFromTopAnimation(): Animation {
        val anim = TranslateAnimation(
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            -1f,
            Animation.RELATIVE_TO_SELF,
            0f,
        ).apply {
            duration = 222
        }
        return anim
    }

    fun slideOutToTopAnimation(onEnd: () -> Unit = {}): Animation {
        val anim = TranslateAnimation(
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            -1f,
        ).apply {
            duration = 222
           // interpolator = BounceInterpolator()
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {
                    onEnd()
                }

                override fun onAnimationRepeat(animation: Animation?) {
                }

            })
        }
        return anim
    }

    fun alertAutoInOutAnimation(): Animation {
        return AnimationSet(false).apply {
            addAnimation(slideInFromTopAnimation())
            addAnimation(slideOutToTopAnimation())
        }
    }

    fun dp2px(context: Context, dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }
}