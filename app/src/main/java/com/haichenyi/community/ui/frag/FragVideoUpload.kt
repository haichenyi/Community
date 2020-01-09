package com.haichenyi.community.ui.frag

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import com.haichenyi.community.R
import com.haichenyi.community.base.BaseApp
import com.haichenyi.community.base.BaseFrag
import com.haichenyi.community.common.showPermissionDialog
import com.haichenyi.community.common.showSettingDialog
import com.haichenyi.community.common.showToast
import com.haichenyi.community.databinding.FragVideoUploadBinding
import com.haichenyi.community.entity.VideoInfo
import com.haichenyi.community.jni.FFmpegCmd
import com.haichenyi.community.utils.Gutil
import com.haichenyi.community.utils.LogUtil
import com.haichenyi.community.vm.FragVideoUploadVm
import kotlinx.android.synthetic.main.frag_video_upload.*
import permissions.dispatcher.*

/**
 * @Author: 海晨忆
 * @Desc: 视频上传类
 * @Date: 2020/1/7-13:39
 */
@RuntimePermissions
class FragVideoUpload : BaseFrag<FragVideoUploadBinding, FragVideoUploadVm>
  (R.layout.frag_video_upload) {

  private lateinit var path: String
  private lateinit var outPath: String
  private lateinit var videoInfo: VideoInfo
  override fun initView(binding: FragVideoUploadBinding, bundle: Bundle?) {
    super.initView(binding, bundle)
    binding.setListener {
      when (it.id) {
        R.id.img -> showPermissionNeedWithPermissionCheck()
        R.id.btnReset -> {
          clVideo.visibility = View.GONE
          img.visibility = View.VISIBLE
        }
        R.id.btnCompress -> {
          FFmpegCmd.transcode(
            path, outPath, 25, 200, videoInfo.width
            , videoInfo.height, videoInfo.duration, "superfast", videoInfo
            , object : FFmpegCmd.ProgressListener {
              override fun onProgressUpdate(progress: Int, timeRemaining: Long) {
                LogUtil.v(LogUtil.LOG_WZ, "$progress%----$timeRemaining")
              }

              override fun onCompressCompleted(filePath: String?) {
                LogUtil.v(LogUtil.LOG_WZ, "压缩完成：$filePath")

              }
            })
        }
      }
    }
  }


  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == PICK_VIDEO_REQUEST && resultCode == RESULT_OK && null != data) {
      val uri = data.data
      val filePathColumn = arrayOf(MediaStore.Video.Media.DATA)
      val cursor = BaseApp.baseApp.contentResolver.query(
        uri!!, filePathColumn, null
        , null, null
      )
      cursor?.moveToFirst()
      val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
      path = cursor?.getString(columnIndex!!)!!
      cursor.close()
      initVideo(path)
    }
  }

  /**
   * @desc: 初始化视频信息
   * @param path 视频路径
   */
  @SuppressLint("SetTextI18n")
  private fun initVideo(path: String) {
    clVideo.visibility = View.VISIBLE
    img.visibility = View.GONE
    tvPath.text = path
    videoInfo = FFmpegCmd.getVideoInfo(path)
    tvDuration.text = "视频时长：" + Gutil.parseTime(videoInfo.duration.toInt() / 1000)
    tvFps.text = "FPS:" + videoInfo.fps
    tvResolution.text = "分辨率：" + videoInfo.width + "x" + videoInfo.height
    tvBitrate.text = "码率：" + Gutil.bitrateFormat(videoInfo.bitrate)
    tvCodec.text = "Video Codec: " + videoInfo.videoCodec
    tvRotation.text = "Video Rotation: " + videoInfo.rotation + "°"

    tvTargetFPS.text = "转码后的FPS:30"
    tvTargetBitrate.text = "转码后的码率:4000"
    outPath = Gutil.getVideoFileDir(context!!) + Gutil.getVideoFileName("")
    tvFileAfter.text = outPath
  }

  @NeedsPermission(
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.READ_EXTERNAL_STORAGE
  )
  fun showPermissionNeed() {
    Intent().apply {
      type = "video/*"
      action = Intent.ACTION_GET_CONTENT
      startActivityForResult(this, PICK_VIDEO_REQUEST)
    }
  }

  @OnShowRationale(
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.READ_EXTERNAL_STORAGE
  )
  fun showRationaleForNeed(request: PermissionRequest) {
    showPermissionDialog(context!!, "上传视频功能需要获取存储权限", request)
  }

  @OnPermissionDenied(
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.READ_EXTERNAL_STORAGE
  )
  fun showDeniedForNeed() {
    showToast("未获取到外部存储权限")
  }

  @OnNeverAskAgain(
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.READ_EXTERNAL_STORAGE
  )
  fun showNeverAskForNeed() {
    showSettingDialog(activity!!, "未获取到存储权限，请前往设置页面设置")
  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    onRequestPermissionsResult(requestCode, grantResults)
  }


  companion object {
    private const val PICK_VIDEO_REQUEST = 0x2
  }
}