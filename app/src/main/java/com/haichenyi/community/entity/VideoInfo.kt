package com.haichenyi.community.entity

data class VideoInfo(
  //旋转角度
  var rotation: Int,
  //宽
  var width: Int,
  //高
  var height: Int,
  //时长
  var duration: Long,
  //码率，bps
  var bitrate: Int,
  //帧数，pfs
  var fps: Int,
  //转码器
  var videoCodec: String
)