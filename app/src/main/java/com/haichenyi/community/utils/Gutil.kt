package com.haichenyi.community.utils

import android.content.Context
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object Gutil {
  fun parseTime(seconds: Int): String {
    var format = "mm:ss"
    when {
      seconds in 600..3599 -> { //>=10min
        format = "mm:ss"
      }
      seconds in 3600..35999 -> { //>=1h
        format = "H:mm:ss"
      }
      seconds >= 36000 -> { //>=10:00:00
        format = "HH:mm:ss"
      }
    }
    val sdf =
      SimpleDateFormat(format, Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("GMT+0")
    return sdf.format(Date((seconds * 1000).toLong()))
  }

  fun bitrateFormat(bitrate: Int): String {
    val df = DecimalFormat("#.00")
    val result: String
    result = if (bitrate < 1024 * 1024) {
      df.format(bitrate / 1024f.toDouble()) + " Kbps"
    } else {
      df.format(bitrate / 1024 / 1024f.toDouble()) + " Mbps"
    }
    return result
  }

  /**
   * @desc: 获取压缩后的视频存放的文件夹绝对路径
   * @param context context对象
   * @return: 返回文件夹的绝对路径
   */
  fun getVideoFileDir(context: Context): String {
    var path: String
    val fileDir = context.cacheDir
    if (!fileDir.exists()) {
      fileDir.mkdirs()
    }
    path = fileDir.absolutePath
    if (!path.endsWith("/")) {
      path = "$path/"
    }
    return path
  }

  /**
   * @desc: 需要生成的文件名称带后缀名
   * @param name 文件名称
   * @return: 文件名称
   */
  fun getVideoFileName(name: String): String {
    return if (name.isEmpty()) {
      val sdf = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault())
      sdf.format(System.currentTimeMillis()) + ".mp4"
    } else {
      "$name.mp4"
    }
  }
}