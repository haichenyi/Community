package com.haichenyi.community.utils

import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import java.lang.ref.WeakReference
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author: 海晨忆
 * @Desc:
 * @Date: 2020/1/9-14:41
 */
object VideoUtils {
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

  /**
   * @desc: 获取视频某一帧的图片
   * @param path 视频路径
   * @param timeUs 微秒
   * @return: Bitmap
   */
  fun getVideoFrame(path: String?, timeUs: Long): WeakReference<Bitmap?> {
    val media = MediaMetadataRetriever()
    try {
      media.setDataSource(path)
    } catch (e: Exception) {
      return WeakReference(null)
    }
    return WeakReference(media.getFrameAtTime(timeUs))
  }
}