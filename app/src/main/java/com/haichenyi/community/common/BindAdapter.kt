package com.haichenyi.community.common

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * @Author: 海晨忆
 * @Desc: DataBinding的自定义属性
 * @Date: 2020/1/3-11:20
 */
@BindingAdapter("fitsSystemWindows")
fun View.fitsSystemWindows(need: Boolean) {
  if (need) {
    val resId = resources.getIdentifier("status_bar_height", "dimen", "android")
    val height = resources.getDimensionPixelSize(resId)
    setPaddingRelative(0, height, 0, 0)
  }
}