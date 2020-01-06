package com.haichenyi.community.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast

/**
 * @Author haichenyi
 * @Desc Toast工具类
 * @Date 2019/7/15-16:58
 * @Home haichenyi.com
 */
object ToastUtils {
  private var toast: Toast? = null

  @SuppressLint("ShowToast")
  fun init(context: Context) {
    if (null == toast) {
      toast = Toast.makeText(context, "", Toast.LENGTH_SHORT)
    }
  }


  fun show(text: String) {
    toast?.setText(text)
    toast?.show()
  }
}