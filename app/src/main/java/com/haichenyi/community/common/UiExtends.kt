package com.haichenyi.community.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.haichenyi.community.utils.ToastUtils

/**
 * @Author: 海晨忆
 * @Desc: 扩展方法的类
 * @Date: 2020/1/3-17:33
 */

inline fun <reified T : Fragment> Fragment.createFrag(bundle: Bundle? = null): T {
  return (childFragmentManager.fragmentFactory.instantiate(
    T::class.java.classLoader!!,
    T::class.java.name
  ) as T).apply {
    arguments = bundle
  }
}

fun showToast(content: String) = ToastUtils.show(content)


