package com.haichenyi.community.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.haichenyi.community.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * @Author: 海晨忆
 * @Desc:
 * @Date: 2020/1/2-16:54
 */
open class BaseAct<VB : ViewDataBinding>(@LayoutRes layoutId: Int) : AppCompatActivity(layoutId),
  HasAndroidInjector {
  @Inject
  lateinit var androidInjector: DispatchingAndroidInjector<Any>

  protected var isLight = true
  protected var statusColor = Color.TRANSPARENT

  override fun androidInjector(): AndroidInjector<Any> = androidInjector

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    initStatus()
    val rootView = findViewById<View?>(R.id.root)
    if (rootView != null) {
      DataBindingUtil.bind<VB>(rootView)?.let {
        initDataBind(it, savedInstanceState)
      }
    }
  }

  /**
   * @desc: 设置状态栏颜色
   */
  private fun initStatus() {
    var visibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    if (isLight) {
      if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
        visibility = visibility.or(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
      }
    }
    window.decorView.systemUiVisibility = visibility
    window.statusBarColor = statusColor
  }

  protected open fun initDataBind(vb: VB, savedInstanceState: Bundle?): Unit = Unit
}