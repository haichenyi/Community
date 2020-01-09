package com.haichenyi.community.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.haichenyi.community.utils.ToastUtils
import permissions.dispatcher.PermissionRequest
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.*

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

/**
 * @desc: 提示用户的为什么获取这个权限的对话框
 * @param context context对象
 * @param content dialog内容
 * @param request 权限回调
 */
fun showPermissionDialog(context: Context, content: String, request: PermissionRequest) {
  AlertDialog.Builder(context).apply {
    setTitle("权限获取")
    setMessage(content)
    setNegativeButton("拒绝") { _, _ -> request.cancel() }
    setPositiveButton("同意") { _, _ -> request.proceed() }
    show()
  }
}

/**
 * @desc: 跳转app设置页面
 * @param activity activity对象
 * @param content dialog内容
 */
fun showSettingDialog(activity: Activity, content: String) {
  AlertDialog.Builder(activity).apply {
    setTitle("权限获取")
    setMessage(content)
    setNegativeButton("取消", null)
    setPositiveButton("去设置") { _, _ -> setPermissionsSetting(activity) }
    show()
  }
}

/**
 * 跳转小米系统的设置页面.
 *
 * @param activity activity
 */
fun setPermissionsSetting(activity: Activity) {
  if (isMiUi()) {
    try { // MIUI 8
      activity.startActivityForResult(
        Intent("miui.intent.action.APP_PERM_EDITOR")
          .setClassName(
            "com.miui.securitycenter",
            "com.miui.permcenter.permissions.PermissionsEditorActivity"
          )
          .putExtra("extra_pkgname", activity.packageName), 1000
      )
    } catch (e: Exception) {
      try { // MIUI 5/6/7
        activity.startActivityForResult(
          Intent("miui.intent.action.APP_PERM_EDITOR")
            .setClassName(
              "com.miui.securitycenter",
              "com.miui.permcenter.permissions.AppPermissionsEditorActivity"
            )
            .putExtra("extra_pkgname", activity.packageName), 1000
        )
      } catch (e1: Exception) { // 否则跳转到应用详情
        activity.startActivityForResult(
          Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            .setData(
              Uri.fromParts(
                "package", activity.packageName,
                null
              )
            ), 1000
        )
      }
    }
  } else {
    activity.startActivityForResult(
      Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        .setData(Uri.fromParts("package", activity.packageName, null)), 1000
    )
  }
}

/**
 * 判断是否是MiUi系统.
 *
 * @return boolean
 */
fun isMiUi(): Boolean {
  val device = Build.MANUFACTURER
  if (TextUtils.equals(device, "Xiaomi")) {
    val prop = Properties()
    try {
      prop.load(
        FileInputStream(
          File(
            Environment.getRootDirectory(),
            "build.prop"
          )
        )
      )
      return prop.getProperty(
        "ro.miui.ui.version.code",
        null
      ) != null || prop.getProperty(
        "ro.miui.ui.version.name",
        null
      ) != null || prop.getProperty("ro.miui.internal.storage", null) != null
    } catch (e: IOException) {
      e.printStackTrace()
    }
  }
  return false
}


