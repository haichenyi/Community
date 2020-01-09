package com.haichenyi.community.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.util.Log
import java.io.File

object MediaTool {
  fun getVideoFrame(path: String?, timeUs: Long): Bitmap? {
    val media = MediaMetadataRetriever()
    try {
      media.setDataSource(path)
    } catch (e: Exception) {
      e.printStackTrace()
      return null
    }
    return media.getFrameAtTime(timeUs)
  }

  fun insertMedia(context: Context, filePath: String) {
    if (!checkFile(filePath)) return
    //保存图片后发送广播通知更新数据库
    val uri = Uri.fromFile(File(filePath))
    context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri))
  }

  private fun checkFile(filePath: String): Boolean {
    val file = File(filePath)
    val result = file.exists()
    if (!result) {
      Log.e("MediaTool", "文件不存在 path = $filePath")
    }
    return result
  }
}