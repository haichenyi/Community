package com.haichenyi.community.vm

import android.content.Intent
import android.provider.MediaStore
import com.haichenyi.community.base.BaseApp
import com.haichenyi.community.base.BaseViewModel
import com.haichenyi.community.entity.VideoInfo
import com.haichenyi.community.jni.FFmpegCmd
import com.haichenyi.community.ui.frag.FragVideoUpload
import javax.inject.Inject

/**
 * @Author: 海晨忆
 * @Desc:
 * @Date: 2020/1/7-13:40
 */
class FragVideoUploadVm @Inject constructor(private val fragment: FragVideoUpload) :
  BaseViewModel() {
  /**
   * @desc: 视频压缩
   * @param inPath 视频原路径
   * @param outPath 压缩后的视频路径
   * @param videoInfo 视频信息
   */
  fun compress(
    inPath: String,
    outPath: String,
    videoInfo: VideoInfo,
    listener: FFmpegCmd.ProgressListener
  ) {
    val fps = if (videoInfo.fps > 30) 30 else videoInfo.fps
    val bps = if (videoInfo.bitrate > 1000 * 1024) 1000 else videoInfo.bitrate
    FFmpegCmd.transCode(
      inPath,
      outPath,
      fps,
      bps,
      videoInfo.width,
      videoInfo.height,
      videoInfo.duration,
      //格式：ultrafast，superfast，veryfast，faster，fast，medium，slow，slower，veryslow
      "superfast",
      videoInfo,
      listener
    )
  }

  /**
   * @desc: 获取需要压缩的文件路径
   * @param data Intent对象
   * @return: String
   */
  fun getFilePath(data: Intent?): String {
    val uri = data!!.data
    val filePathColumn = arrayOf(MediaStore.Video.Media.DATA)
    val cursor = BaseApp.baseApp.contentResolver.query(
      uri!!, filePathColumn, null
      , null, null
    )
    cursor?.moveToFirst()
    val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
    val path = cursor?.getString(columnIndex!!)!!
    cursor.close()
    return path
  }
}